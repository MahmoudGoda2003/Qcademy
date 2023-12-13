package com.example.backend.teacher;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.teacher.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;


    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        return "hello world from " + Role.TEACHER.name();
    }

    @PostMapping("/createCourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseMainInfoDTO course) {
        return teacherService.createCourse(course);
    }

    @PostMapping("/addModules")
    public ResponseEntity<String> addModules() {
        return null;
    }
    
}
