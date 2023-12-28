package com.example.backend.course.model;


import com.example.backend.course.model.Assignment;
import com.example.backend.student.model.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Setter
@Getter
@Table(name = "SolvedAssigment_data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolvedAssignment {

    @Column(name = "solution_url", nullable = false)
    private String solutionURL;

    @Column(name = "grade")
    private short grade;


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_number")
    private Assignment assignments;


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
}
