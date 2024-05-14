package com.example.backend.course.dto;

import com.example.backend.course.model.Assignment;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
public class AssignmentDTO {
    private static final ModelMapper modelMapper = new ModelMapper();
    private short number;
    @NotBlank(message = "Assignment Name is mandatory")
    private String name;
    @NotBlank(message = "Assignment URL is mandatory")
    private String assignmentURL;

    public static AssignmentDTO convert(Assignment assignment) {
        return modelMapper.map(assignment, AssignmentDTO.class);
    }
}
