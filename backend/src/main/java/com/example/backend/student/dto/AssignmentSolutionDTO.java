package com.example.backend.student.dto;

import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.model.Assignment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
@Getter
@Setter
@NoArgsConstructor
public class AssignmentSolutionDTO {
    @NotBlank(message = "Assignment number is mandatory")
    private Integer assignmentNumber;

    @NotBlank(message = "Solution Link is mandatory")
    private String solutionLink;
}
