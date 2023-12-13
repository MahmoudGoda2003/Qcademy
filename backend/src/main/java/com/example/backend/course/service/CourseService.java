package com.example.backend.course.service;

import com.example.backend.course.model.Course;
import com.example.backend.course.courseModule.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course deleteCourse(int courseId) {
        return courseRepository.deleteById(courseId);
    }

    public Optional<Course> findCourse(int courseId) {
        return courseRepository.findById(courseId);
    }
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

}
