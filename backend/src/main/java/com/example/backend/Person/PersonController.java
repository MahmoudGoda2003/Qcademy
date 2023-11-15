package com.example.backend.Person;

import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.service.PersonService;
import com.example.backend.exceptions.exceptions.WrongDataEnteredException;
import com.example.backend.exceptions.exceptions.DataNotFoundException;
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
    public ResponseEntity<String> signUp(@RequestBody String email) throws Exception {
        String status = personService.signUp(email);
        return switch (status) {
            case "0" -> new ResponseEntity<>(HttpStatus.OK);
            case "1" -> throw new WrongDataEnteredException("Email is already in use");
            default -> throw new Exception(status);
        };
    }

    @PostMapping("/signup/validate")
    public ResponseEntity<String> validatePerson(@RequestBody SignUpDTO signUpD) throws Exception {
        String status = personService.validatePerson(signUpD, signUpD.getCode());
        return switch (status) {
            case "0" -> new ResponseEntity<>(HttpStatus.CREATED);
            case "1" -> throw new DataNotFoundException("Wrong code, try again");
            case "2" -> throw new DataNotFoundException("Try to sign up again");
            default -> throw new Exception(status);
        };
    }
}
