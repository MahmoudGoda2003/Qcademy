package com.example.backend.course.dto;


import com.example.backend.course.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@NoArgsConstructor
public class CourseMainInfoDTO {

    private static final ModelMapper modelMapper = new ModelMapper();
    private int courseId;
    @NotBlank(message = "Course name is mandatory")
    private String name;
    @NotBlank(message = "Course description is mandatory")
    private String description;
    @NotBlank(message = "Course photo link is mandatory")
    private String photoLink;
    @NotBlank(message = "Course rating are mandatory")
    private float rating;
    @NotBlank(message = "Course estimated time is mandatory")
    private short estimatedTime;
    @NotBlank(message = "Course tags are mandatory")
    private List<String> tags;
    private String teacherName;
    @NotBlank(message = "start date is mandatory")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid date format. Use DD-MM-YYYY")
    private String startDate;

    public static CourseMainInfoDTO convert(Course course) {
        return modelMapper.map(course, CourseMainInfoDTO.class);
    }
}
