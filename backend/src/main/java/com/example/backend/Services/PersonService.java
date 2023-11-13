package com.example.backend.Services;

import com.example.backend.model.Person;
import com.example.backend.repository.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean savePerson(Person person){
        try{
            String nonEncodedPass = person.getEncryptedPassword();
            String encodedPass = passwordEncoder.encode(nonEncodedPass);
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
            String encodedPass = passwordEncoder.encode(non_encoded_pass);
            Person person = new Person(firstName, lastName, email, encodedPass, DOB, photoLink);
            personRepository.save(person);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    public Boolean validatePasswordByEmail(String email, String password){
        if(personRepository.existsByEmail(email)){
            Person person = personRepository.findByEmail(email);
            if (passwordEncoder.matches(password, person.getEncryptedPassword())) {
                return true;
            }
        }
        return false;
    }
}
