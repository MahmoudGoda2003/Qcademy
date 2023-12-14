package com.example.backend.teacher.service;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.service.CourseModuleService;
import com.example.backend.course.service.CourseService;
import com.example.backend.course.service.LectureService;
import com.example.backend.teacher.model.Teacher;
import com.example.backend.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    private final CourseService courseService;
    private final TeacherRepository teacherRepository;

    private final LectureService lectureService;
    private final CourseModuleService courseModuleService;
    @Autowired
    public TeacherService(CourseService courseService,
                          LectureService lectureService,
                          CourseModuleService courseModuleService,
                          TeacherRepository teacherRepository) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.courseModuleService = courseModuleService;
        this.teacherRepository = teacherRepository;
    }

    public void addTeacher(Long id) {
        Teacher teacher = new Teacher(id);
        teacherRepository.save(teacher);
    }

    public ResponseEntity<String> createCourse(CourseMainInfoDTO courseMainInfoDTO) {
        Course course = Course.convert(courseMainInfoDTO);
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Teacher teacher = teacherRepository.getByUserId(userId);
        course.setTeacher(teacher);
        if (teacher.getCourses() == null) teacher.setCourses(new ArrayList<>());
        teacher.getCourses().add(course);
        teacherRepository.updateCoursesByUserId(teacher.getUserId(), teacher.getCourses());
        courseService.saveCourse(course);
        return new ResponseEntity<>("CourseCreated", HttpStatus.CREATED);
    }
    public ResponseEntity<String> createLecture(LectureDTO lectureDTO) {
        lectureService.addLectureToModule(lectureDTO);
        return new ResponseEntity<>("Lecture Created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> createModule(CourseModuleDTO courseModuleDTO) {
        courseModuleService.createCourseModule(courseModuleDTO);
        return new ResponseEntity<>("Module Created successfully", HttpStatus.CREATED);
    }
    public ResponseEntity<String> updateModules() {
        return null;
    }
}
