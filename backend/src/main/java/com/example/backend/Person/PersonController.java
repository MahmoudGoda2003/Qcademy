package com.example.backend.person;

import com.example.backend.person.dto.LoginDTO;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Role;
import com.example.backend.person.service.PersonService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;




@RequestMapping("/person")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(HttpServletResponse response, @RequestBody String email) throws Exception {
        return personService.sendValidationCode(response, email);
    }

    @PostMapping("/signup/validate")
    public ResponseEntity<String> validateOTP(HttpServletRequest request, @Valid @RequestBody SignUpDTO signUpDTO) throws Exception {
        return personService.validateOTP(request, signUpDTO);
    }

    @PostMapping("/google")
    public ResponseEntity<PersonMainInfoDTO> googleSignIn(HttpServletResponse response, @RequestBody String accessToken) throws Exception {
        return personService.signInUsingGoogle(response, accessToken);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonMainInfoDTO> logIn(HttpServletResponse response, @Valid @RequestBody LoginDTO loginDTO) throws Exception {
        return personService.login(response, loginDTO.getEmail(), loginDTO.getPassword());
    }

    @GetMapping("/test")
    public String test() throws Exception {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        Thread.sleep(5000);
        return "hello world from " + Role.STUDENT.name();
    }
}
