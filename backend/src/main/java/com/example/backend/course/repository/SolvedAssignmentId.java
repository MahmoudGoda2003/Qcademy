package com.example.backend.course.repository;

import com.example.backend.course.model.Assignment;
import com.example.backend.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class SolvedAssignmentId implements Serializable {
    private Assignment assignment;
    private Student student;

}
