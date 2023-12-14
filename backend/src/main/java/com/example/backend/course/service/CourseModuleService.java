package com.example.backend.course.service;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.courseModule.repository.CourseRepository;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.repository.CourseModuleRepository;
import com.example.backend.exceptions.exception.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class CourseModuleService  {
    private final CourseModuleRepository courseModuleRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseModuleService(CourseModuleRepository courseModuleRepository,
                               CourseRepository courseRepository){
        this.courseModuleRepository = courseModuleRepository;
        this.courseRepository = courseRepository;
    }

    private CourseModule saveCourseModule(CourseModule courseModule) {
        return courseModuleRepository.save(courseModule);
    }

    // save a course module for a course with the given courseId
    public void createCourseModule(CourseModuleDTO courseModuleDTO) {
        CourseModule savedModule = saveCourseModule(CourseModule.convert(courseModuleDTO));
        Course course = courseRepository.findById(courseModuleDTO.getCourseId());
        if(course == null)
            throw new DataNotFoundException("Course not found");

        if(course.getModule() == null)
            course.setModule(new ArrayList<>());
        course.getModule().add(savedModule);
        courseRepository.save(course);
        System.out.println(savedModule.getWeekNumber());
    }

}
