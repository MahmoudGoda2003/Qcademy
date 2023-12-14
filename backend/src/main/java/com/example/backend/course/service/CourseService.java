package com.example.backend.course.service;

import com.example.backend.course.courseModule.repository.CourseRepository;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.model.Course;
import com.example.backend.teacher.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CourseMainInfoDTO courseMainInfoDTO, Teacher teacher) {
        Course course = Course.convert(courseMainInfoDTO);
        course.setTeacher(teacher);
        this.courseRepository.save(course);
        return course;
    }
}
