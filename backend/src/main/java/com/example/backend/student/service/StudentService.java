package com.example.backend.student.service;

import com.example.backend.course.repository.CourseRepository;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.promotion.service.PromotionService;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final PromotionService promotionService;
    private final StudentRepository studentRepository;
    private final CourseRepository coursesRepository;

    private final CourseService coursesService;

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
        Student student = this.studentRepository.getByUserId(userId);
        List<CourseMainInfoDTO> courses = new ArrayList<>();
        for (Course course : student.getCourses()) {
            CourseMainInfoDTO courseMainInfoDTO = CourseMainInfoDTO.convert(course);
            courseMainInfoDTO.setEnrolled(true);
            courses.add(courseMainInfoDTO);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    public ResponseEntity<List<CourseMainInfoDTO>> getRecommendedCourses(){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = this.studentRepository.getByUserId(userId);
        List<Course> allCourses = this.coursesRepository.findCoursesNotEnrolledByStudent(student);
        List<CourseMainInfoDTO> courses = new ArrayList<>();
        for (Course course : allCourses) {
            courses.add(CourseMainInfoDTO.convert(course));
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    public ResponseEntity<String> enrollCourse(int courseId){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = this.studentRepository.getByUserId(userId);
        Course course = this.coursesService.enrollInCourse(courseId, student);
        student.getCourses().add(course);
        this.studentRepository.save(student);
        return new ResponseEntity<>("Course enrolled successfully", HttpStatus.OK);
    }

}
