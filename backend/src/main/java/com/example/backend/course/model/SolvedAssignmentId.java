package com.example.backend.course.model;

import com.example.backend.student.model.Student;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolvedAssignmentId  implements Serializable {
    private Assignment assignments;
    private Student student;


}
