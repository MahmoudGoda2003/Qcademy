package com.example.backend.student;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/student/")
@CrossOrigin(allowCredentials = "True", origins = "http://localhost:3000", allowedHeaders = )
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final AssignmentService assignmentService;

    @PostMapping("requestPromotion")
    public ResponseEntity<String> requestPromotion() {
        return studentService.requestPromotion();
    }

    @GetMapping("enrolledCourses")
    public ResponseEntity<List<CourseMainInfoDTO>> getEnrolledCourses() {
        return studentService.getEnrolledCourses();
    }

    @GetMapping("recommendedCourses")
    public ResponseEntity<List<CourseMainInfoDTO>> getRecommendedCourses() {
        return studentService.getRecommendedCourses();
    }

    @PostMapping("enrollCourse")
    public ResponseEntity<String> enrollCourse(@RequestParam int courseId) {
        return studentService.enrollCourse(courseId);
    }

    @GetMapping("courseModules")
    public ResponseEntity<List<CourseModuleDTO>> getCourseModules(@RequestParam int courseId) {
        return this.courseService.getCourseModules(courseId);
    }

    @GetMapping("getGrades")
    public ResponseEntity<List<SolvedAssignmentDTO>> getGrades(@RequestParam Long studentId){
        return assignmentService.getGrades();
    }

    @PostMapping("submitAssignment")
    public ResponseEntity<String> submitAssignment(@RequestBody SolvedAssignmentDTO solvedAssignmentDTO){
        System.out.println(solvedAssignmentDTO.toString());
        return assignmentService.submitAssignment(solvedAssignmentDTO);
    }
}
