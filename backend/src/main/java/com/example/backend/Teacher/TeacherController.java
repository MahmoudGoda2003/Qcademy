package com.example.backend.Teacher;

import com.example.backend.Person.model.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher/")
public class TeacherController {

    @GetMapping("test")
    public String test() throws Exception {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        return "hello world from " + Role.STUDENT.name();
    }
}
