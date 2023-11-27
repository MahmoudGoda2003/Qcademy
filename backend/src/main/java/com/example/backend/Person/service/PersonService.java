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
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OTPRepository OTPRepository;
    @Autowired
    private MailSenderService mailSenderService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final Random random = new Random();

    public void savePerson(Person person){
        String nonEncodedPass = person.getEncryptedPassword();
        String encodedPass = encoder.encode(nonEncodedPass);
        person.setEncryptedPassword(encodedPass);
        personRepository.save(person);
    }

    private boolean notValidatedPassword(String email, String password) {
        String savedPassword = personRepository.findEncryptedPasswordByEmail(email);
        return savedPassword == null|| !encoder.matches(password, savedPassword);
    }

   public ResponseEntity<PersonInfoDTO> login(String email, String password){
        if(notValidatedPassword(email, password)) {
            throw new LoginDataNotValidException("password or email isn't valid");
        }
        Person person = personRepository.findByEmail(email);
        return new ResponseEntity<>(PersonInfoDTO.convert(person), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> sendOTP(String email) throws MessagingException {
        String OTP = String.valueOf(random.nextInt(100000, 999999));
        return sendOTP(email, OTP);
    }

    public ResponseEntity<String> sendOTP(String email, String otp) throws MessagingException {
        if (Boolean.TRUE.equals(personRepository.existsByEmail(email))) {
            throw new WrongDataEnteredException("Email is already in use");
        }
        OTP OTP = new OTP(email, encoder.encode(otp));
        OTPRepository.save(OTP);
        mailSenderService.sendNewMail(email, otp);
        return new ResponseEntity<>("Email accepted", HttpStatus.OK);
    }

    public ResponseEntity<String> validateOTP(SignUpDTO signUpDTO) {
        OTP otp = OTPRepository.findOTPByEmail(signUpDTO.getEmail());
        if (otp == null) {
            throw new DataNotFoundException("Try to sign up again");
        }
        Duration timeDifference = Duration.between(otp.getTimeCreated(), Instant.now());
        if (timeDifference.toMinutes() > 30) {
            throw new DataNotFoundException("Try to sign up again");
        }
        if (!encoder.matches(signUpDTO.getCode(), otp.getOTP())) {
            throw new WrongDataEnteredException("Wrong code, try again");
        }
        OTPRepository.deleteById(signUpDTO.getEmail());
        savePerson(new Person(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }
}
