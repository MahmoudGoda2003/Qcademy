package com.example.backend.student;

import com.example.backend.person.model.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/")
public class StudentController {
    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.STUDENT.name();
    }
}
