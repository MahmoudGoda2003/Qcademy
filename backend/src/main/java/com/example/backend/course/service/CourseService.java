package com.example.backend.course.service;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.courseModule.repository.CourseRepository;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public ResponseEntity<String> createCourse(CourseMainInfoDTO courseMainInfoDTO) {
        this.saveCourse(Course.convert(courseMainInfoDTO));
        return new ResponseEntity<>("CourseCreated", HttpStatus.CREATED);
    }

    public Course deleteCourse(int courseId) {
        return courseRepository.deleteById(courseId);
    }

    public Course findCourse(int courseId) {
        return courseRepository.findById(courseId);
    }
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

}
