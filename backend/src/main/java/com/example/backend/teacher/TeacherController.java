package com.example.backend.teacher;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.service.CourseModuleService;
import com.example.backend.course.service.CourseService;
import com.example.backend.course.service.LectureService;
import com.example.backend.person.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final CourseService courseService;
    private final LectureService lectureService;
    private final CourseModuleService courseModuleService;
    @Autowired
    public TeacherController(CourseService courseService,
                             LectureService lectureService,
                             CourseModuleService courseModuleService)
    {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.courseModuleService = courseModuleService;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        return "hello world from " + Role.TEACHER.name();
    }

    @PostMapping("/createCourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseMainInfoDTO course) {
        return courseService.createCourse(course);
    }

    @PostMapping("/CreateLecture")
    public ResponseEntity<String> createLecture(@RequestBody LectureDTO lectureDTO) {
        return  lectureService.createLecture(lectureDTO);
    }

    @PostMapping("/CreateModule")
    public ResponseEntity<String> createModule(@RequestBody CourseModuleDTO courseModuleDTO) {
        return  courseModuleService.createModule(courseModuleDTO);
    }
}
