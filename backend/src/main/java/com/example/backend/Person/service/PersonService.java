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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private final Random random = new Random();

    public PersonService(PersonRepository personRepository, OTPRepository OTPRepository,
                         MailSenderService mailSenderService){
        this.personRepository = personRepository;
        this.mailSenderService = mailSenderService;
        this.OTPRepository = OTPRepository;
    }


    public void savePerson(Person person){
        String nonEncodedPass = person.getEncryptedPassword();
        String encodedPass = encoder.encode(nonEncodedPass);
        person.setEncryptedPassword(encodedPass);
        personRepository.save(person);
    }

    private boolean notValidatedPassword(String email, String password) {
        String savedPassword = personRepository.findPasswordByEmail(email);
        return savedPassword == null|| !encoder.matches(password, savedPassword);
    }

   public ResponseEntity<PersonInfoDTO> login(String email, String password){
        if(notValidatedPassword(email, password))
            throw new LoginDataNotValidException("password or email isn't valid");
        Person person = personRepository.findByEmail(email);
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
        savePerson(new Person(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }
}
