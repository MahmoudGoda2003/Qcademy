package com.example.backend.course.dto;

import com.example.backend.course.assigment.model.Assigment;
import com.example.backend.course.model.Course;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;

public class AssigmentDTO {
    private short number;
    @NotBlank(message = "Assigment Name is mandatory")
    private String name;

    private String assigmentURL;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static AssigmentDTO convert(Assigment assigment) {
        return modelMapper.map(assigment, AssigmentDTO.class);
    }
}
