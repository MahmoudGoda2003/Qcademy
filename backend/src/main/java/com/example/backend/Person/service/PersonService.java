package com.example.backend.Person.service;

import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.OTPs;
import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.OTPRepository;
import com.example.backend.Person.repository.PersonRepository;
import com.example.backend.Services.MailSenderService;
import com.example.backend.exceptions.exceptions.DataNotFoundException;
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

    public PersonService(PersonRepository personRepository, OTPRepository OTPRepository, MailSenderService mailSenderService){
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

   public boolean validatePasswordByEmail(String email, String password){
        try{
            Person person = personRepository.findByEmail(email);
            if(person != null){
                return encoder.matches(password, person.getEncryptedPassword());
            }
            return false;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    public ResponseEntity<String> sendOTP(String email) {
        if (personRepository.existsByEmail(email)) throw new WrongDataEnteredException("Email is already in use");
        String OTP = String.valueOf(random.nextInt(100000, 999999));
        OTPs OTPs = new OTPs(email, encoder.encode(OTP));
        OTPRepository.save(OTPs);
        mailSenderService.sendNewMail(email, OTP);
        System.out.println(OTP);
        return new ResponseEntity<>("Email accepted", HttpStatus.OK);
    }

    public ResponseEntity<String> validatePerson(SignUpDTO signUpDTO) {
        OTPs OTPs = OTPRepository.findByEmail(signUpDTO.getEmail());
        if (OTPs == null) throw new DataNotFoundException("Try to sign up again");
        if (!encoder.matches(signUpDTO.getCode(), OTPs.getOTP()))
            throw new WrongDataEnteredException("Wrong code, try again");
        OTPRepository.deleteById(signUpDTO.getEmail());
        savePerson(new Person(signUpDTO));
        return new ResponseEntity<>("SignUp completed", HttpStatus.CREATED);
    }
}
