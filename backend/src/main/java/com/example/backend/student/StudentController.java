package com.example.backend.student;

import com.example.backend.person.model.Role;
import com.example.backend.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/")
public class StudentController {
    private final StudentService  studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("requestPromotion")
    public ResponseEntity<String> requestPromotion() throws Exception {
        return studentService.requestPromotion();
    }

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.STUDENT.name();
    }
}
