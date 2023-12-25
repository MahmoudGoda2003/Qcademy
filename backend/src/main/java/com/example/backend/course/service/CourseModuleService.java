package com.example.backend.course.service;

import com.example.backend.course.assignment.model.Assignment;
import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.lecture.model.Lecture;
import com.example.backend.course.model.Course;
import com.example.backend.course.courseModule.repository.CourseModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        //TODO:fix this IDK
        List<Lecture> lectures = new ArrayList<>();
        for (LectureDTO lectureDTO : courseModuleDTO.getLectures()) {
            Lecture lecture = lectureService.createLecture(lectureDTO);
            lectures.add(lecture);
        }
        courseModule.setLectures(lectures);

        List<Assignment> assignments = new ArrayList<>();
        for (AssignmentDTO assignmentDTO : courseModuleDTO.getAssignments()) {
            Assignment assignment = this.assignmentService.createAssignment(assignmentDTO);
            assignments.add(assignment);
        }
        courseModule.setAssignments(assignments);
        return this.courseModuleRepository.save(courseModule);
    }

}
