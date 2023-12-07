package com.example.backend.person;

import com.example.backend.person.dto.LoginDTO;
import com.example.backend.person.dto.PersonInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.service.PersonService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signUp(HttpServletResponse response, @RequestBody String email) throws MessagingException {
        return personService.sendValidationCode(response, email);
    }

    @PostMapping("/signup/validate")
    public ResponseEntity<String> validateOTP(HttpServletRequest request, @RequestBody SignUpDTO signUpDTO) {
        return personService.validateOTP(request, signUpDTO);
    }

    @PostMapping("/google")
    public ResponseEntity<PersonInfoDTO> googleSignIn(HttpServletResponse response, @RequestBody String accessToken) {
        return personService.signInUsingGoogle(response, accessToken);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonInfoDTO> logIn(HttpServletResponse response, @RequestParam LoginDTO loginDTO) {
        return personService.login(response, loginDTO.getEmail(), loginDTO.getPassword());
    }
}
