package com.example.backend.course.assigment.model;

import com.example.backend.student.model.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class SolvedAssigmentId implements Serializable {

    private Assigment assignments;

    private Student student;
}
