package com.example.backend.course.service;

import com.example.backend.course.assignment.model.Assignment;
import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.lecture.model.Lecture;
import com.example.backend.course.model.Course;
import com.example.backend.course.courseModule.repository.CourseModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseModuleService {
    private final CourseModuleRepository courseModuleRepository;
    private final LectureService lectureService;
    private final AssignmentService assignmentService;

    @Autowired
    public CourseModuleService(CourseModuleRepository courseModuleRepository, LectureService lectureService, AssignmentService assigmentService) {
        this.courseModuleRepository = courseModuleRepository;
        this.lectureService = lectureService;
        this.assignmentService = assigmentService;
    }


    public CourseModule createCourseModule(CourseModuleDTO courseModuleDTO) {
        CourseModule courseModule = CourseModule.convert(courseModuleDTO);

        if (courseModuleDTO.getLectures() != null && !courseModuleDTO.getLectures().isEmpty()) {
            Lecture lecture = this.lectureService.createLecture(courseModuleDTO.getLectures().get(0));
            courseModule.setLectures(List.of(lecture));
        }

        if (courseModuleDTO.getAssignments() != null && !courseModuleDTO.getAssignments().isEmpty()) {
            Assignment assignment = this.assignmentService.createAssignment(courseModuleDTO.getAssignments().get(0));
            courseModule.setAssignments(List.of(assignment));
        }

        return this.courseModuleRepository.save(courseModule);
    }

}
