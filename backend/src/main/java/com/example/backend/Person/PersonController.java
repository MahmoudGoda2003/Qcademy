package com.example.backend.Person;

import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/signup/page")
    public ResponseEntity<Integer> signUp(@RequestBody String email) {
        return new ResponseEntity<>(personService.signUp(email), HttpStatus.OK);
    }

    @PostMapping("/signup/validate")
    public ResponseEntity<Integer> validatePerson(@RequestBody SignUpDTO signUpD) {
        return new ResponseEntity<>(personService.validatePerson(signUpD, signUpD.getCode()), HttpStatus.CREATED);
    }
}
