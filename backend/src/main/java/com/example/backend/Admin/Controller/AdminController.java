package com.example.backend.Admin.Controller;

import com.example.backend.Person.model.Role;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/")
@Async
public class AdminController {

    @GetMapping("test")
    public String test() throws MessagingException {
        System.out.println("hello world");
        return Role.STUDENT.name();
    }
}
