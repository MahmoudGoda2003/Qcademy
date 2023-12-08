package com.example.backend.Admin;

import com.example.backend.Person.model.Role;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("test")
    public String test() throws Exception {
        System.out.println("hello world");
        return "hello world from " + Role.ADMIN.name();
    }
}
