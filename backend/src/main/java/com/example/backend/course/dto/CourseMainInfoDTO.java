package com.example.backend.course.dto;


import com.example.backend.course.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseMainInfoDTO {

    private int courseId;

    @NotBlank(message = "Course name is mandatory")
    private String name;

    @NotBlank(message = "Course tags are mandatory")
    private String description;

    @NotBlank(message = "Course tags are mandatory")
    private String photoLink;

    @NotBlank(message = "Course tags are mandatory")
    private float rating;

    @NotBlank(message = "Course tags are mandatory")
    private short estimatedTime;

    @NotBlank(message = "Course tags are mandatory")
    private List<String> tags;

    private String teacherName;

    @NotBlank(message = "start date is mandatory")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid date format. Use DD-MM-YYYY")
    private String startDate;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static CourseMainInfoDTO convert(Course course) {
        return modelMapper.map(course, CourseMainInfoDTO.class);
    }
}
