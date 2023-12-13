package com.example.backend.course.dto;

import com.example.backend.course.courseModule.model.CourseModule;
import jakarta.validation.constraints.NotBlank;

public class AssigmentDTO {
    private short number;
    @NotBlank(message = "Assigment Name is mandatory")
    private String name;

    private String assigmentURL;
    private CourseModuleDTO module;

}
