package com.example.backend.person;

import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.dto.SolvedAssignmentDTO;
import com.example.backend.course.service.AssignmentService;
import com.example.backend.person.dto.*;
import com.example.backend.person.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/person/")
@AllArgsConstructor
@RestController
//@CrossOrigin(allowCredentials = "True", origins = "http://localhost:3000")
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

}
