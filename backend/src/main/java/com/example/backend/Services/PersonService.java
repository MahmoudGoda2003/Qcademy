package com.example.backend.Services;

import com.example.backend.DTO.SignUpDTO;
import com.example.backend.model.NotValidatedPerson;
import com.example.backend.model.Person;
import com.example.backend.repository.NotValidatedPersonRepository;
import com.example.backend.repository.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotValidatedPersonRepository notValidatedPersonRepository;

    public PersonService(PersonRepository personRepository, NotValidatedPersonRepository notValidatedPersonRepository){
        this.personRepository = personRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.notValidatedPersonRepository = notValidatedPersonRepository;
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
        try{
            Person person = personRepository.findByEmail(email);
            if(person != null){
                return passwordEncoder.matches(password, person.getEncryptedPassword());
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

    public int signUp(String email) {
        try {
            Person person = personRepository.findByEmail(email);
            if (person != null) return 1;
            NotValidatedPerson notValidatedPerson = new NotValidatedPerson(email);
            if (notValidatedPersonRepository.findByEmail(email) != null) {
                notValidatedPersonRepository.deleteById(email);
            }
            notValidatedPersonRepository.save(notValidatedPerson);
            return notValidatedPerson.getOTP();
        } catch (Exception e){
            System.out.println(e.toString());
            return 2;
        }
    }

    public Person validatePerson(SignUpDTO signUpDTO, int OTP) {
        try {
            NotValidatedPerson notValidatedPerson = notValidatedPersonRepository.findByEmail(signUpDTO.getEmail());
            if (notValidatedPerson != null) {
                if (notValidatedPerson.getOTP() != OTP) return null;
                notValidatedPersonRepository.deleteById(signUpDTO.getEmail());
                savePerson(new Person(signUpDTO));
                return personRepository.findByEmail(signUpDTO.getEmail());
            }
            return personRepository.findByEmail(signUpDTO.getEmail());
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
