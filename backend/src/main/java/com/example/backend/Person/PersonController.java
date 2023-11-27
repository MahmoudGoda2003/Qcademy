package com.example.backend.Person;

import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.service.PersonService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/")
@RestController
public class PersonController {

    private final PersonService personService;



    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/signup")
    @Async
    public ResponseEntity<String> signUp(@RequestBody String email) throws MessagingException {
        return personService.sendOTP(email);
    }

    @PostMapping("/signup/validate")
    @Async
    public ResponseEntity<String> validateOTP(@RequestBody SignUpDTO signUpDTO){
        return personService.validateOTP(signUpDTO);
    }
}
