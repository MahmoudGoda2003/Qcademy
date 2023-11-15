package com.example.backend.Person.service;

import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.NotValidatedPerson;
import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.NotValidatedPersonRepository;
import com.example.backend.Person.repository.PersonRepository;
import com.example.backend.Services.MailSenderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder encoder;
    private final NotValidatedPersonRepository notValidatedPersonRepository;

    private final MailSenderService mailSenderService;

    Runnable deleteNotValidatedPerson = new Runnable() {
        @Override
        public void run() {
            notValidatedPersonRepository.deleteByTimeCreatedLessThan(System.currentTimeMillis() - 3600000);
        }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public PersonService(PersonRepository personRepository, NotValidatedPersonRepository notValidatedPersonRepository, MailSenderService mailSenderService){
        this.personRepository = personRepository;
        this.mailSenderService = mailSenderService;
        this.encoder = new BCryptPasswordEncoder();
        this.notValidatedPersonRepository = notValidatedPersonRepository;
        executorService.scheduleAtFixedRate(deleteNotValidatedPerson, 0, 5, TimeUnit.MINUTES);
    }

    public boolean savePerson(Person person){
        try{
            String nonEncodedPass = person.getEncryptedPassword();
            String encodedPass = encoder.encode(nonEncodedPass);
            person.setEncryptedPassword(encodedPass);
            personRepository.save(person);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
    public boolean savePerson(String firstName, String lastName, String email, String non_encoded_pass, String DOB, String photoLink){
        try{
            String encodedPass = encoder.encode(non_encoded_pass);
            Person person = new Person(firstName, lastName, email, encodedPass, DOB, photoLink);
            personRepository.save(person);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

   public Boolean validatePasswordByEmail(String email, String password){
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

    public Person getByEmail(String email) {
        try {
            return personRepository.findByEmail(email);
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public boolean hasPerson(String email) {
        return personRepository.findByEmail(email) != null;
    }

    public String signUp(String email) {
        try {
            if (personRepository.existsByEmail(email)) return "1";
            Random random = new Random();
            int OTP = random.nextInt(100000, 999999);
            NotValidatedPerson notValidatedPerson = new NotValidatedPerson(email, encoder.encode(String.valueOf(OTP)));
            notValidatedPersonRepository.deleteById(email);
            notValidatedPersonRepository.save(notValidatedPerson);
            mailSenderService.sendNewMail(email, String.valueOf(OTP));
            return "0";
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String validatePerson(SignUpDTO signUpDTO, String OTP) {
        try {
            NotValidatedPerson notValidatedPerson = notValidatedPersonRepository.findByEmail(signUpDTO.getEmail());
            if (notValidatedPerson != null) {
                if (!encoder.matches(OTP, notValidatedPerson.getOTP()))
                    return "1";
                notValidatedPersonRepository.deleteById(signUpDTO.getEmail());
                savePerson(new Person(signUpDTO));
                return "0";
            }
            return "2";
        } catch (Exception e){
            return e.toString();
        }
    }
}
