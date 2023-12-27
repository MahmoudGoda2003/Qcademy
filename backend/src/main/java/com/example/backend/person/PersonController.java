package com.example.backend.person;

import com.example.backend.person.dto.LoginDTO;
import com.example.backend.person.dto.PersonInfoDTO;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/person/")
public class PersonController {

    private final PersonService personService;

    @PostMapping("signup")
    public ResponseEntity<String> signUp(HttpServletResponse response, @RequestBody String email) throws Exception {
        return personService.sendValidationCode(response, email);
    }

    @PostMapping("signup/validate")
    public ResponseEntity<String> validateOTP(HttpServletRequest request, @Valid @RequestBody SignUpDTO signUpDTO) throws Exception {
        return personService.validateOTP(request, signUpDTO);
    }

    @Generated
    @PostMapping("google")
    public ResponseEntity<PersonMainInfoDTO> googleSignIn(HttpServletResponse response, @RequestBody String accessToken) throws Exception {
        return personService.signInUsingGoogle(response, accessToken);
    }

    @PostMapping("login")
    public ResponseEntity<PersonMainInfoDTO> logIn(HttpServletResponse response, @Valid @RequestBody LoginDTO loginDTO) throws Exception {
        return personService.login(response, loginDTO.getEmail(), loginDTO.getPassword());
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@Valid @RequestBody PersonInfoDTO personInfoDTO) {
        return personService.updatePerson(personInfoDTO);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        return personService.logout(response);
    }
}
