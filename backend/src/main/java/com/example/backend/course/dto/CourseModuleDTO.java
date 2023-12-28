package com.example.backend.course.dto;


import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.model.Lecture;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CourseModuleDTO {


    private static final ModelMapper modelMapper = new ModelMapper();
    @NotBlank(message = "Course Module date is mandatory")
    private String name;
    private List<String> slidesSets;
    private List<String> quizzes;
    @NotBlank(message = "Course Module's Course is mandatory")
    private int courseId;
    private List<LectureDTO> lectures;
    private List<AssignmentDTO> assignments;

    public static CourseModuleDTO convert(CourseModule courseModule) {
        return modelMapper.map(courseModule, CourseModuleDTO.class);
    }

}
