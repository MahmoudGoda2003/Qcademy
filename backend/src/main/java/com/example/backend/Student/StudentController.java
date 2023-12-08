package com.example.backend.Student;

import com.example.backend.Person.model.Role;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/")
public class StudentController {
    @GetMapping("test")
    public String test() throws Exception {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        Thread.sleep(5000);
        return "hello world from " + Role.STUDENT.name();
    }
}
