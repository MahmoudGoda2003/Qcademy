package com.example.backend.course.dto;

import com.example.backend.course.model.SolvedAssignment;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class SolvedAssignmentDTO {
    @NotBlank(message = "Solution URL is mandatory")
    private String solutionURL;

    private short grade;

    @NotBlank(message = "Assignment number is mandatory")
    private short assignmentNumber;

    private Long studentId;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static SolvedAssignmentDTO convert(SolvedAssignment solvedAssignment) {
        return modelMapper.map(solvedAssignment, SolvedAssignmentDTO.class);
    }

}
