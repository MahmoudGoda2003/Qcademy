package com.example.backend.teacher.service;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.service.CourseService;
import com.example.backend.person.model.Role;
import com.example.backend.promotion.service.PromotionService;
import com.example.backend.teacher.model.Teacher;
import com.example.backend.teacher.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final CourseService courseService;
    private final TeacherRepository teacherRepository;
    private final PromotionService promotionService;

    public ResponseEntity<String> requestPromotion() {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        promotionService.requestPromotion(userId, Role.ADMIN);
        return new ResponseEntity<>("Promotion request successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<String> createCourse(CourseMainInfoDTO courseMainInfoDTO) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Teacher teacher = teacherRepository.getByUserId(userId);
        Course createdCourse = courseService.createCourse(courseMainInfoDTO, teacher);
        teacher.getCourses().add(createdCourse);
        teacherRepository.save(teacher);
        return new ResponseEntity<>(String.format("{'CourseCreated':%d}", createdCourse.getCourseId()), HttpStatus.CREATED);
    }

    public ResponseEntity<String> createModule(CourseModuleDTO courseModuleDTO) {
        CourseModule courseModule = this.courseService.addModule(courseModuleDTO);
        return new ResponseEntity<>(String.format("{'ModuleCreated':%d}", courseModule.getWeekNumber()), HttpStatus.CREATED);
    }

    public ResponseEntity<List<CourseMainInfoDTO>> getCreatedCourses() {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Teacher teacher = teacherRepository.getByUserId(userId);
        List<CourseMainInfoDTO> courses = new ArrayList<>();
        for (Course course : teacher.getCourses()) {
            courses.add(CourseMainInfoDTO.convert(course));
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
