package com.example.backend.student.service;

import com.example.backend.course.courseModule.repository.CourseRepository;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.promotion.service.PromotionService;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final PromotionService promotionService;
    private final StudentRepository studentRepository;
    private final CourseRepository coursesRepository;

    private final CourseService coursesService;

    @Autowired
    public StudentService(PromotionService promotionService, StudentRepository studentRepository, CourseRepository coursesRepository, CourseService coursesService) {
        this.promotionService = promotionService;
        this.studentRepository = studentRepository;
        this.coursesRepository = coursesRepository;
        this.coursesService = coursesService;
    }

    public void saveStudent(Long userId){
        Student student = new Student(userId);
        this.studentRepository.save(student);
    }
    public ResponseEntity<String> requestPromotion(){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        promotionService.requestPromotion(userId, Role.TEACHER);
        return new ResponseEntity<>("Promotion request successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<CourseMainInfoDTO>> getEnrolledCourses(){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = this.studentRepository.findByUserId(userId);
        List<CourseMainInfoDTO> courses = new ArrayList<>();
        for (Course course : student.getCourses()) {
            courses.add(CourseMainInfoDTO.convert(course));
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    public ResponseEntity<List<CourseMainInfoDTO>> getRecommendedCourses(){
        List<Course> allCourses = this.coursesRepository.findAll();
        List<CourseMainInfoDTO> courses = new ArrayList<>();
        for (Course course : allCourses) {
            courses.add(CourseMainInfoDTO.convert(course));
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    public ResponseEntity<String> enrollCourse(int courseId){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = this.studentRepository.findByUserId(userId);
        Course course = this.coursesService.enrollInCourse(courseId, student);
        student.getCourses().add(course);
        this.studentRepository.save(student);
        return new ResponseEntity<>("Course enrolled successfully", HttpStatus.OK);
    }

}
