package com.example.backend.course.model;


import com.example.backend.course.model.Assignment;
import com.example.backend.student.model.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SolvedAssigment_data")
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
