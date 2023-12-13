package com.example.backend.Person;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
@RequestMapping("/person")
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

    @PostMapping("/google")
    public ResponseEntity<PersonInfoDTO> googleSignIn(HttpServletResponse response, @RequestBody String accessToken) {
        return personService.signInUsingGoogle(response, accessToken);
    }

    @PostMapping("/login")
    //TODO: change to @requestBody later and use a DTO
    public ResponseEntity<PersonInfoDTO> logIn(HttpServletResponse response, @RequestParam String email, @RequestParam String password) {
        return personService.login(response, email, password);
    }
}
