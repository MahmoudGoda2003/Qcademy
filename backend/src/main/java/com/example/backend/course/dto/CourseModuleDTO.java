package com.example.backend.course.dto;


import com.example.backend.course.assigment.model.Assignment;
import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.lecture.model.Lecture;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseModuleDTO {


    @NotBlank(message = "Course Module date is mandatory")
    private String publishDate;

    private List<String> slidesSets;

    private List<String> quizzes;

    @NotBlank(message = "Course Module's Course is mandatory")
    private int courseId;

    private List<LectureDTO> lectures;

    private List<AssignmentDTO> assignments;


    private static final ModelMapper modelMapper = new ModelMapper();

    public static CourseModuleDTO convert(CourseModule courseModule) {

        CourseModuleDTO courseModuleDTO = modelMapper.map(courseModule, CourseModuleDTO.class);
        List<LectureDTO> lectureDTOs = new ArrayList<>();
        for (Lecture lecture : courseModule.getLectures()) {
            LectureDTO lectureDTO = LectureDTO.convert(lecture);
            lectureDTOs.add(lectureDTO);
        }
        courseModuleDTO.setLectures(lectureDTOs);

        List<AssignmentDTO> assignmentDTOs = new ArrayList<>();
        for (Assignment assignment : courseModule.getAssignments()) {
            AssignmentDTO assignmentDTO = AssignmentDTO.convert(assignment);
            assignmentDTOs.add(assignmentDTO);
        }
        courseModuleDTO.setAssignments(assignmentDTOs);
        return courseModuleDTO;
    }

}
