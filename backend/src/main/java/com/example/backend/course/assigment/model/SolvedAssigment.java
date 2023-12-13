package com.example.backend.course.assigment.model;


import com.example.backend.student.model.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SolvedAssigment_data")
@IdClass(SolvedAssigmentId.class)
public class SolvedAssigment {

    @Column(name = "solution_url", nullable = false)
    private String solutionURL;

    @Column(name = "grade")
    private short grade;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "assigment_module_course_id"),
            @JoinColumn(name = "assigment_module_week_number"),
            @JoinColumn(name = "assigment_number")
    })
    private Assigment assigment;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
