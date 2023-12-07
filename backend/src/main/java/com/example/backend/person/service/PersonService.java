package com.example.backend.person.service;

import com.example.backend.exceptions.exception.DataNotFoundException;
import com.example.backend.exceptions.exception.LoginDataNotValidException;
import com.example.backend.exceptions.exception.WrongDataEnteredException;
import com.example.backend.person.dto.PersonInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.services.CookiesService;
import com.example.backend.services.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class PersonService {

    private final MailSenderService mailSenderService;
    private final Authenticator authenticator;
    private final PersonRepository personRepository;
    private final CookiesService cookiesService;
    private final PasswordEncoder encoder;
    private final Random random;

    @Autowired
    public PersonService(PersonRepository personRepository, MailSenderService mailSenderService) {
        this.personRepository = personRepository;
        this.mailSenderService = mailSenderService;
        this.authenticator = new Authenticator();
        this.encoder = new BCryptPasswordEncoder();
        this.random = new Random();
        this.cookiesService = new CookiesService();
    }

    public void savePerson(Person person) {
        String nonEncodedPass = person.getPassword();
        String encodedPass = this.encoder.encode(nonEncodedPass);
        person.setPassword(encodedPass);
        this.personRepository.save(person);
    }

    public ResponseEntity<PersonInfoDTO> login(HttpServletResponse response, String email, String password) {
        Person person = this.personRepository.findByEmail(email);
        if (person == null || !this.encoder.matches(password, person.getPassword()))
            throw new LoginDataNotValidException("password or email isn't valid");
        String token = this.authenticator.createToken(person, false, false);
        Cookie sissionCookie = this.cookiesService.createCookie("qcademy", token, 24 * 60 * 60);
        if (response != null) response.addCookie(sissionCookie);
        return new ResponseEntity<>(PersonInfoDTO.convert(person), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> sendValidationCode(HttpServletResponse response, String email) throws MessagingException {
        String code = String.valueOf(this.random.nextInt(100000, 999999));
        return sendValidationCode(response, email, code);
    }

    public ResponseEntity<String> sendValidationCode(HttpServletResponse response, String email, String code) throws MessagingException {
        Person person = this.personRepository.findByEmail(email);
        if (person != null) {
            throw new WrongDataEnteredException("Email already exists");
        }
        String validationCode = code + email;
        String encodedValidationCode = this.encoder.encode(validationCode);
        Cookie validationCookie = this.cookiesService.createCookie("validationCode", encodedValidationCode, 60 * 30);
        response.addCookie(validationCookie);
        mailSenderService.sendNewMail(email, code);
        return new ResponseEntity<>("Validation code sent", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> validateOTP(HttpServletRequest request, SignUpDTO signUpDTO) {
        Cookie validationCookie = cookiesService.getCookie(request, "validationCode");
        if (validationCookie == null) {
            throw new DataNotFoundException("Try to sign up again");
        }
        if (!encoder.matches(signUpDTO.getCode() + signUpDTO.getEmail(), validationCookie.getValue())) {
            throw new WrongDataEnteredException("Wrong code, try again");
        }
        savePerson(Person.convert(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }

    public Person getGoogleObject(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken, HttpMethod.GET, httpEntity, String.class);
        JSONObject object = new JSONObject(entity.getBody());
        return new Person(object);
    }

    public ResponseEntity<PersonInfoDTO> signInUsingGoogle(HttpServletResponse response, String accessToken) {
            Person person = getGoogleObject(accessToken);
            if (personRepository.findByEmail(person.getEmail()) == null) {
                savePerson(person);
            }
            return login(response, person.getEmail(), person.getPassword());
    }
}
