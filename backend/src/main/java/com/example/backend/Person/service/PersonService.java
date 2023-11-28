package com.example.backend.Person.service;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.OTP;
import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.OTPRepository;
import com.example.backend.Person.repository.PersonRepository;
import com.example.backend.Services.MailSenderService;
import com.example.backend.exceptions.exceptions.DataNotFoundException;
import com.example.backend.exceptions.exceptions.LoginDataNotValidException;
import com.example.backend.exceptions.exceptions.WrongDataEnteredException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.Random;

@Service
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private final OTPRepository OTPRepository;
    @Autowired
    private final MailSenderService mailSenderService;
    private final Authenticator authenticator = new Authenticator();
    private final Random random = new Random();

    public PersonService(PersonRepository personRepository, OTPRepository OTPRepository,
                         MailSenderService mailSenderService){
        this.personRepository = personRepository;
        this.mailSenderService = mailSenderService;
        this.OTPRepository = OTPRepository;
    }


    public void savePerson(Person person){
        String nonEncodedPass = person.getPassword();
        String encodedPass = encoder.encode(nonEncodedPass);
        person.setPassword(encodedPass);
        personRepository.save(person);
    }

   public ResponseEntity<PersonInfoDTO> login(HttpServletResponse response, String email, String password){
        Person person = personRepository.findByEmail(email);
        if(person==null||!encoder.matches(password, person.getPassword()))
            throw new LoginDataNotValidException("password or email isn't valid");
        String token = authenticator.createToken(person, false, false);
        if (response!=null) response.addCookie(createSessionCookie(token));
        return new ResponseEntity<>(PersonInfoDTO.convert(person), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> sendOTP(String email) {
        String OTP = String.valueOf(random.nextInt(100000, 999999));
        return sendOTP(email, OTP);
    }

    public ResponseEntity<String> sendOTP(String email, String otp) {
        if (personRepository.existsByEmail(email))
            throw new WrongDataEnteredException("Email is already in use");
        OTP OTP = new OTP(email, encoder.encode(otp));
        OTPRepository.save(OTP);
        mailSenderService.sendNewMail(email, otp);
        return new ResponseEntity<>("Email accepted", HttpStatus.OK);
    }

    public ResponseEntity<String> validateOTP(SignUpDTO signUpDTO) {
        String OTP = OTPRepository.findOTPByEmail(signUpDTO.getEmail());
        if (OTP == null) throw new DataNotFoundException("Try to sign up again");
        if (!encoder.matches(signUpDTO.getCode(), OTP))
            throw new WrongDataEnteredException("Wrong code, try again");
        OTPRepository.deleteById(signUpDTO.getEmail());
        savePerson(Person.convert(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }

    private Person getGoogleObject(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken, HttpMethod.GET, httpEntity, String.class);
        JSONObject object = new JSONObject(entity.getBody());
        return new Person(object);
    }

    public Cookie createSessionCookie(String token) {
        Cookie cookie = new Cookie("qcademy", token);
        cookie.setMaxAge(24*60*60);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }

    public Cookie deleteCookie() {
        Cookie cookie = new Cookie("qcademy", null);
        cookie.setMaxAge(0);
        return cookie;
    }

    public ResponseEntity<PersonInfoDTO> signInUsingGoogle(HttpServletResponse response, String accessToken) {
        Person person = getGoogleObject(accessToken);
        if (personRepository.existsByEmail(person.getEmail())) return login(response, person.getEmail(), person.getPassword());
        savePerson(person);
        String token = authenticator.createToken(person, false, false);
        if (response!=null) response.addCookie(createSessionCookie(token));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PersonInfoDTO.convert(person));
    }
}
