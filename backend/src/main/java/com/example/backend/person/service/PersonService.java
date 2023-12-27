package com.example.backend.person.service;

import com.example.backend.exceptions.exception.*;
import com.example.backend.person.dto.PersonInfoDTO;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.services.CookiesService;
import com.example.backend.services.JwtService;
import com.example.backend.services.MailSenderService;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.student.service.StudentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Generated;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


@Service
public class PersonService {
    private final MailSenderService mailSenderService;
    private final JwtService authenticator;
    private final PersonRepository personRepository;
    private final CookiesService cookiesService;
    private final PasswordEncoder encoder;
    private final Random random;
    private final String secretKey;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public PersonService(PersonRepository personRepository, MailSenderService mailSenderService, StudentRepository studentRepository, StudentService studentService) {
        this.personRepository = personRepository;
        this.mailSenderService = mailSenderService;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.authenticator = new JwtService();
        this.encoder = new BCryptPasswordEncoder();
        this.random = new Random();
        this.cookiesService = new CookiesService();
        this.secretKey = new StandardEnvironment().getProperty("QcademyAuthKey");
    }


    @Transactional
    public Person savePerson(Person person) {
        String nonEncodedPass = person.getPassword();
        String encodedPass = this.encoder.encode(nonEncodedPass);
        person.setPassword(encodedPass);
        Person savedPerson = this.personRepository.save(person);
        this.studentService.saveStudent(savedPerson.getId());
        return savedPerson;
    }

    public ResponseEntity<PersonMainInfoDTO> login(HttpServletResponse response, String email, String password) throws Exception {
        Person person = this.personRepository.findByEmail(email);
        if (person == null) {
            throw new LoginDataNotValidException();
        }
        return login(response, person, password, false);
    }

    private ResponseEntity<PersonMainInfoDTO> login(HttpServletResponse response, Person person, String password, boolean isGoogle) {
        if (!isGoogle && !encoder.matches(password, person.getPassword())) throw new LoginDataNotValidException();
        String token = this.authenticator.createToken(person.getRole(), person.getId());
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
            throw new EmailNotValidException();
        }
        if (personRepository.existsPersonByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
        String encodedValidationCode = this.cookiesService.hashCode(code + email + this.secretKey);
        Cookie validationCookie = this.cookiesService.createCookie("validationCode", encodedValidationCode, 60 * 30);
        response.addCookie(validationCookie);
        mailSenderService.sendNewMail(email, code);
        return new ResponseEntity<>("Validation code sent", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> validateOTP(HttpServletRequest request, SignUpDTO signUpDTO) throws Exception {
        if (personRepository.existsPersonByEmail(signUpDTO.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        Cookie validationCookie = cookiesService.getCookie(request, "validationCode");
        if (validationCookie == null) {
            throw new ValidationCodeExpiredException();
        }

        if (!validationCookie.getValue().equals(this.cookiesService.hashCode(signUpDTO.getCode() + signUpDTO.getEmail() + this.secretKey))) {
            throw new WrongValidationCodeException();
        }
        savePerson(Person.convert(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }

    @Generated
    public Person getGoogleObject(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken, HttpMethod.GET, httpEntity, String.class);
        JSONObject object = new JSONObject(entity.getBody());
        final String encodedPassword = this.encoder.encode(object.getString("id") + System.nanoTime());
        object.put("password", encodedPassword);
        return new Person(object);
    }

    @Generated
    public ResponseEntity<PersonMainInfoDTO> signInUsingGoogle(HttpServletResponse response, String accessToken) throws Exception {
        Person person = getGoogleObject(accessToken);
        Person temp = this.personRepository.findByEmail(person.getEmail());
        if (temp == null) {
            person = savePerson(person);
        } else {
            person = temp;
        }
        return login(response, person, person.getPassword(), true);
    }

    public Role getUserRole(Long userId) {
        return personRepository.findRoleById(userId);
    }

    public void setUserRole(Long userId, Role newRole) {
        personRepository.updateRoleById(userId, newRole);
    }

    public ResponseEntity<String> updatePerson(PersonInfoDTO personInfoDTO) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        personRepository.updatePerson(userId, personInfoDTO.getFirstName(), personInfoDTO.getLastName(), personInfoDTO.getBio(), personInfoDTO.getPhotoLink(), personInfoDTO.getDateOfBirth());
        return new ResponseEntity<>("Data Updated", HttpStatus.ACCEPTED);
    }
}
