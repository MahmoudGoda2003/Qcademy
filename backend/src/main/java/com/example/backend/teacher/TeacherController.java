package com.example.backend.teacher;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/")
//@CrossOrigin(allowCredentials = "True", origins = "http://localhost:3000")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;
    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.TEACHER.name();
    }

    @PostMapping("createCourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseMainInfoDTO course) {
        return this.teacherService.createCourse(course);
    }

    @PostMapping("createModule")
    public ResponseEntity<String> createModule(@RequestBody CourseModuleDTO courseModuleDTO) {
        return  teacherService.createModule(courseModuleDTO);
    }

    @PostMapping("requestPromotion")
    public ResponseEntity<String> requestPromotion() throws Exception {
        return teacherService.requestPromotion();
    }

    @GetMapping("createdCourses")
    public ResponseEntity<List<CourseMainInfoDTO>> getCreatedCourses(){
        return teacherService.getCreatedCourses();
    }

    @GetMapping("courseModules")
    public ResponseEntity<List<CourseModuleDTO>> getCourseModules(@RequestParam int courseId){
        return this.courseService.getCourseModules(courseId);
    }
}
