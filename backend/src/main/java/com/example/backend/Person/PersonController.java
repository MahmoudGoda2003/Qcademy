package com.example.backend.Person;

import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.service.PersonService;
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

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody String email) throws Exception {
        return personService.sendOTP(email);
    }

    @PostMapping("/signup/validate")
    public ResponseEntity<String> validatePerson(@RequestBody SignUpDTO signUpDTO) throws Exception {
        return personService.validatePerson(signUpDTO);
    }
}
