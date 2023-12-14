package com.example.backend.course.service;

import com.example.backend.course.assigment.model.Assignment;
import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.lecture.model.Lecture;
import com.example.backend.course.model.Course;
import com.example.backend.course.repository.CourseModuleRepository;
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
    private final AssigmentService assigmentService;

    @Autowired
    public CourseModuleService(CourseModuleRepository courseModuleRepository, LectureService lectureService, AssigmentService assigmentService) {
        this.courseModuleRepository = courseModuleRepository;
        this.lectureService = lectureService;
        this.assigmentService = assigmentService;
    }


    public CourseModule createCourseModule(CourseModuleDTO courseModuleDTO, Course course) {
        CourseModule courseModule = CourseModule.convert(courseModuleDTO);
        courseModule.setAssignments(null);
        courseModule.setLectures(null);
        courseModule = this.courseModuleRepository.save(courseModule);
        List<Lecture> lectures = new ArrayList<>();
        for (LectureDTO lectureDTO : courseModuleDTO.getLectures()) {
            Lecture lecture = lectureService.createLecture(lectureDTO, courseModule);
            lectures.add(lecture);
        }
        courseModule.setLectures(lectures);

        List<Assignment> assignments = new ArrayList<>();
        for (AssignmentDTO assignmentDTO : courseModuleDTO.getAssignments()) {
            Assignment assignment = this.assigmentService.createAssigment(assignmentDTO, courseModule);
            assignments.add(assignment);
        }
        courseModule.setCourse(course);
        return this.courseModuleRepository.save(courseModule);
    }

}
