package com.example.backend.course.dto;


import com.example.backend.course.assigment.model.Assigment;
import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.lecture.model.Lecture;
import com.example.backend.course.model.Course;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.model.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CourseModuleDTO {

    @NotBlank(message = "Course Module number is mandatory")
    private int weekNumber;

    @NotBlank(message = "Course Module date is mandatory")
    private String publishDate;

    private ArrayList<String> slidesURL;

    private String quizURL;

    @NotBlank(message = "Course Module's Course is mandatory")
    private Course course;

}
