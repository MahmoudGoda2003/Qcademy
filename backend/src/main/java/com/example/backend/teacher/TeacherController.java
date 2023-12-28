package com.example.backend.teacher;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.teacher.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teacher/")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @PostMapping("createCourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseMainInfoDTO course) {
        return this.teacherService.createCourse(course);
    }

    @PostMapping("createModule")
    public ResponseEntity<String> createModule(@RequestBody CourseModuleDTO courseModuleDTO) {
        return teacherService.createModule(courseModuleDTO);
    }

    @PostMapping("requestPromotion")
    public ResponseEntity<String> requestPromotion() {
        return teacherService.requestPromotion();
    }

    @GetMapping("createdCourses")
    public ResponseEntity<List<CourseMainInfoDTO>> getCreatedCourses() {
        return teacherService.getCreatedCourses();
    }

    @GetMapping("courseModules")
    public ResponseEntity<List<CourseModuleDTO>> getCourseModules(@RequestParam int courseId) {
        return this.courseService.getCourseModules(courseId);
    }

    @DeleteMapping("removeCourse")
    public ResponseEntity<String> removeCourse(@RequestParam int courseId) {
        return this.courseService.removeCourse(courseId);
    }

    @DeleteMapping("removeModule")
    public ResponseEntity<String> removeModule(@RequestParam int courseId, @RequestParam int weekNumber) {
        return this.courseService.removeModule(courseId, weekNumber);
    }
}
