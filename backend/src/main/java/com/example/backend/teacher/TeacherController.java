package com.example.backend.teacher;

import com.example.backend.teacher.service.TeacherService;
import com.example.backend.person.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping("requestPromotion")
    public ResponseEntity<String> requestPromotion() throws Exception {
        return teacherService.requestPromotion();
    }

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.TEACHER.name();
    }
}
