package com.example.backend.student;

import com.example.backend.course.model.Course;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/")
public class StudentController {
    private final CourseService courseService;

    public StudentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.STUDENT.name();
    }

    @GetMapping("getCourses")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.findAllCourses(), HttpStatus.OK);
    }
}
