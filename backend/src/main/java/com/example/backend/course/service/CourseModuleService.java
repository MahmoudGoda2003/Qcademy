package com.example.backend.course.service;

import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.model.Lecture;
import com.example.backend.course.repository.CourseModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseModuleService {
    private final CourseModuleRepository courseModuleRepository;

    public CourseModule createCourseModule(CourseModuleDTO courseModuleDTO) {
        CourseModule courseModule = CourseModule.convert(courseModuleDTO);
        return this.courseModuleRepository.save(courseModule);
    }

}
