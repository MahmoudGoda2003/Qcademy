package com.example.backend.Person;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/person")
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
    public ResponseEntity<String> validateOTP(@RequestBody SignUpDTO signUpDTO) throws Exception {
        return personService.validateOTP(signUpDTO);
    }

    @PostMapping("/google")
    public ResponseEntity<PersonInfoDTO> googleSignIn(HttpServletResponse response, @RequestBody String accessToken) {
        return personService.signInUsingGoogle(response, accessToken);
    }

    @GetMapping("/login")
    public ResponseEntity<PersonInfoDTO> logIn(HttpServletResponse response, @RequestParam String email, @RequestParam String password) {
        return personService.login(response, email, password);
    }
}
