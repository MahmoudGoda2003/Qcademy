package com.example.backend.course.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "Assigment_data")
public class Assigment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "assigment_number")
    private int number;

    @Column(name = "assigment_name", nullable = false)
    private String name;

    @Column(name = "assigment_url", nullable = false)
    private String assigmentURL;

    @ManyToOne
    @JoinColumn(name = "module")
    private CourseModule module;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assigment")
    private ArrayList<SolvedAssigment> solvedAssigment;
}
