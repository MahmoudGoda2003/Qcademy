package com.example.backend.person.service;

import com.example.backend.exceptions.exception.DataNotFoundException;
import com.example.backend.exceptions.exception.LoginDataNotValidException;
import com.example.backend.exceptions.exception.WrongDataEnteredException;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.services.CookiesService;
import com.example.backend.services.MailSenderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.StandardEnvironment;
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
    private final String secretKey;

    @Autowired
    public PersonService(PersonRepository personRepository, MailSenderService mailSenderService) {
        this.personRepository = personRepository;
        this.mailSenderService = mailSenderService;
        this.authenticator = new Authenticator();
        this.encoder = new BCryptPasswordEncoder();
        this.random = new Random();
        this.cookiesService = new CookiesService();
        this.secretKey = new StandardEnvironment().getProperty("QcademyAuthKey");
    }


    private void savePerson(Person person) {
        String nonEncodedPass = person.getPassword();
        String encodedPass = this.encoder.encode(nonEncodedPass);
        person.setPassword(encodedPass);
        this.personRepository.save(person);
    }

    public ResponseEntity<PersonMainInfoDTO> login(HttpServletResponse response, String email, String password) throws Exception {
        Person person = this.personRepository.findByEmail(email);
        if (person == null) {
            throw new LoginDataNotValidException("password or email isn't valid");
        }
        String encodedPassword = this.cookiesService.hashCode(password + email + this.secretKey);
        return login(response, person, encodedPassword, false);
    }

    private ResponseEntity<PersonMainInfoDTO> login(HttpServletResponse response, Person person, String password, boolean isGoogle) {
        if (!isGoogle && !password.equals(person.getPassword()))
            throw new LoginDataNotValidException("password or email isn't valid");
        String token = this.authenticator.createToken(person, false, false);
        Cookie sissionCookie = this.cookiesService.createCookie("qcademy", token, 24 * 60 * 60);
        response.addCookie(sissionCookie);
        return new ResponseEntity<>(PersonMainInfoDTO.convert(person), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> sendValidationCode(HttpServletResponse response, String email) throws Exception {
        String code = String.valueOf(this.random.nextInt(100000, 999999));
        return sendValidationCode(response, email, code);
    }

    public ResponseEntity<String> sendValidationCode(HttpServletResponse response, String email, String code) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new WrongDataEnteredException("Email is empty");
        }
        if (personRepository.existsPersonByEmail(email)) {
            throw new WrongDataEnteredException("Email already exists");
        }
        String encodedValidationCode = this.cookiesService.hashCode(code + email + this.secretKey);
        Cookie validationCookie = this.cookiesService.createCookie("validationCode", encodedValidationCode, 60 * 30);
        response.addCookie(validationCookie);
        mailSenderService.sendNewMail(email, code);
        return new ResponseEntity<>("Validation code sent", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> validateOTP(HttpServletRequest request, SignUpDTO signUpDTO) {
        if (personRepository.existsPersonByEmail(signUpDTO.getEmail())) {
            throw new WrongDataEnteredException("Email already exists");
        }

        Cookie validationCookie = cookiesService.getCookie(request, "validationCode");
        if (validationCookie == null) {
            throw new DataNotFoundException("Try to sign up again");
        }

        if (!encoder.matches(signUpDTO.getCode() + signUpDTO.getEmail(), validationCookie.getValue())) {
            throw new WrongDataEnteredException("Wrong code, please try again");
        }
        savePerson(Person.convert(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }

    private Person getGoogleObject(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken, HttpMethod.GET, httpEntity, String.class);
        JSONObject object = new JSONObject(entity.getBody());
        String encodedPassword = this.cookiesService.hashCode(object.getString("id") + object.getString("email") + this.secretKey);
        object.put("id", encodedPassword);
        return new Person(object);
    }

    public ResponseEntity<PersonMainInfoDTO> signInUsingGoogle(HttpServletResponse response, String accessToken) throws Exception {
        Person person = getGoogleObject(accessToken);
        if (!personRepository.existsPersonByEmail(person.getEmail())) {
            savePerson(person);
        }
        return login(response, person, person.getPassword(), true);
    }
}
