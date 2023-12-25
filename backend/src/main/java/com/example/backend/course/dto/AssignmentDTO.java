package com.example.backend.course.dto;

import com.example.backend.course.assignment.model.Assignment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
public class AssignmentDTO {
    private short number;

    @NotBlank(message = "Assignment Name is mandatory")
    private String name;

    @NotBlank(message = "Assignment URL is mandatory")
    private String assignmentURL;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static AssignmentDTO convert(Assignment assignment) {
        return modelMapper.map(assignment, AssignmentDTO.class);
    }
}
