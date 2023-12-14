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
import com.example.backend.person.model.Role;
import com.example.backend.promotion.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final CourseService courseService;
    private final TeacherRepository teacherRepository;

    private final PromotionService promotionService;

    private final LectureService lectureService;
    private final CourseModuleService courseModuleService;
    @Autowired
    public TeacherService(CourseService courseService,
                          LectureService lectureService,
                          CourseModuleService courseModuleService,
                          TeacherRepository teacherRepository,
                          PromotionService promotionService) {
        this.promotionService = promotionService;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.courseModuleService = courseModuleService;
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<String> requestPromotion(){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        promotionService.requestPromotion(userId, Role.ADMIN);
        return new ResponseEntity<>("Promotion request successfully", HttpStatus.CREATED);

    }

    public void addTeacher(Long id) {
        Teacher teacher = new Teacher(id);
        teacherRepository.save(teacher);
    }

    public ResponseEntity<String> createCourse(CourseMainInfoDTO courseMainInfoDTO) {
        Course course = Course.convert(courseMainInfoDTO);
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Teacher teacher;
        if (teacherRepository.existsByUserId(userId)) teacher = teacherRepository.getByUserId(userId);
        else  {
            teacher = new Teacher(userId);
            teacherRepository.save(teacher);
        }
        course.setTeacher(teacher);
        List<Course> list;
        if (teacher.getCourses() == null) list = new ArrayList<>();
        else list = teacher.getCourses();
        list.add(course);
        teacher.setCourses(list);
        teacherRepository.save(teacher);
        courseService.saveCourse(course);
        System.out.println(course.getCourseId());
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
