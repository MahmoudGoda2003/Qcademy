package com.example.backend.course.service;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.repository.CourseRepository;
import com.example.backend.student.model.Student;
import com.example.backend.teacher.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseModuleService courseModuleService;


    public Course createCourse(CourseMainInfoDTO courseMainInfoDTO, Teacher teacher) {
        Course course = Course.convert(courseMainInfoDTO);
        course.setTeacher(teacher);
        this.courseRepository.save(course);
        return course;
    }

    public Course enrollInCourse(int courseId, Student student) {
        Course course = this.courseRepository.getByCourseId(courseId);
        course.getStudents().add(student);
        this.courseRepository.save(course);
        return course;
    }

    public CourseModule addModule(CourseModuleDTO courseModuleDTO) {
        Course course = this.courseRepository.getByCourseId(courseModuleDTO.getCourseId());
        CourseModule courseModule = this.courseModuleService.createCourseModule(courseModuleDTO);
        course.getModules().add(courseModule);
        this.courseRepository.save(course);
        return courseModule;
    }

    public ResponseEntity<List<CourseModuleDTO>> getCourseModules(int courseId) {
        Course course = this.courseRepository.getByCourseId(courseId);
        List<CourseModuleDTO> courseModulesDTO = new ArrayList<>();
        for (CourseModule courseModule : course.getModules()) {
            courseModulesDTO.add(CourseModuleDTO.convert(courseModule));
        }
        return new ResponseEntity<>(courseModulesDTO, HttpStatus.OK);
    }

    public ResponseEntity<String> removeCourse(int courseId) {
        this.courseRepository.deleteById(courseId);
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> removeModule(int courseId,int weekNumber) {
        Course course = this.courseRepository.getByCourseId(courseId);
        CourseModule courseModule = course.getModules().stream().filter(module -> module.getWeekNumber() == weekNumber).findFirst().orElse(null);
        this.courseModuleService.removeModule(courseModule);
        course.getModules().remove(courseModule);
        this.courseRepository.save(course);
        return new ResponseEntity<>("Module deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> changeRate(int courseId,int rating) {
       Course course = this.courseRepository.getByCourseId(courseId);
       course.setTotalRate(course.getTotalRate()+rating);
       course.setNumberOfRates(course.getNumberOfRates()+1);
       this.courseRepository.save(course);
       return new ResponseEntity<>("Rate changed successfully", HttpStatus.OK);
    }
}
