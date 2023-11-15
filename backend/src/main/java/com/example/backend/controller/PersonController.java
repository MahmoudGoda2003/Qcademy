package com.example.backend.controller;

import com.example.backend.DTO.PersonMainInfoDTO;
import com.example.backend.DTO.SignUpDTO;
import com.example.backend.Services.MailSenderService;
import com.example.backend.Services.PersonService;
import com.example.backend.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/")
@RestController
public class PersonController {

    private final PersonService personService;
    private final MailSenderService mailSenderService;

    public PersonController(PersonService personService, MailSenderService mailSenderService) {
        this.personService = personService;
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/signup/page")
    public ResponseEntity<Integer> signUp(@RequestBody String email) {
        int state = personService.signUp(email);
        if (state>5) {
            String subject = "Qcademy SignUp";
            String body1 = """
                    Welcome to Qcademy, this email is sent for validation, you don't need to reply
                    This email address tried to sign up in Qcademy, if you do recognise this attempt, please enter the following number in the box that appeared to you to validate the email

                    """;
            String body2 = "\n\nOr you can press the link below\n\n";
            String body3 = """
                    If you don't recognise this action, please ignore this email.

                    Thanks for your time
                    Qcademy team.
                    """;
            mailSenderService.sendNewMail(email, subject, body1 + state + body2 + body3);
            return new ResponseEntity<>(0, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @PostMapping("/signup/validate")
    public ResponseEntity<Integer> validatePerson(@RequestBody SignUpDTO signUpD) {

        return new ResponseEntity<>(personService.validatePerson(signUpD, Integer.parseInt(signUpD.getCode())), HttpStatus.CREATED);
    }
}
