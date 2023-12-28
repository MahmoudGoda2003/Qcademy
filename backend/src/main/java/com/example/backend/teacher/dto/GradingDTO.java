package com.example.backend.teacher.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradingDTO {
    @NotBlank(message = "Assignment number is mandatory")
    private Integer assignmentNumber;
    @NotBlank(message = "Solution Link is mandatory")
    private Long studentId;
    @NotBlank(message = "Grade is mandatory")
    private short grade;
}
