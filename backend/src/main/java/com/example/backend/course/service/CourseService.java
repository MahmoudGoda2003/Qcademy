package com.example.backend.course.service;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.courseModule.repository.CourseRepository;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Course;
import com.example.backend.student.model.Student;
import com.example.backend.teacher.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseModuleService courseModuleService;


    @Autowired
    public CourseService(CourseRepository courseRepository, CourseModuleService courseModuleService) {
        this.courseRepository = courseRepository;
        this.courseModuleService = courseModuleService;
    }

    public Course createCourse(CourseMainInfoDTO courseMainInfoDTO, Teacher teacher) {
        Course course = Course.convert(courseMainInfoDTO);
        course.setTeacher(teacher);
        this.courseRepository.save(course);
        return course;
    }

    public Course enrollInCourse(int courseId, Student student){
        Course course = this.courseRepository.findById(courseId);
        course.getStudents().add(student);
        this.courseRepository.save(course);
        return course;
    }

    public CourseModule addModule(CourseModuleDTO courseModuleDTO){
         Course course = this.courseRepository.findById(courseModuleDTO.getCourseId());
         CourseModule courseModule = this.courseModuleService.createCourseModule(courseModuleDTO, course);
         course.getModule().add(courseModule);
         this.courseRepository.save(course);
         return courseModule;
    }

    public List<CourseModuleDTO> getCourseModules(int courseId){
        Course course = this.courseRepository.findById(courseId);
        List<CourseModuleDTO> courseModulesDTO = new ArrayList<>();
        for (CourseModule courseModule : course.getModule()) {
            courseModulesDTO.add(CourseModuleDTO.convert(courseModule));
        }
        return courseModulesDTO;
    }


}
