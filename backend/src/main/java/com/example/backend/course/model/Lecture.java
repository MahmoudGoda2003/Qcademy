package com.example.backend.course.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Lecture_data")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "lecture_number")
    private short Number;

    @Column(name = "lecture_url", nullable = false)
    private String videoURL;

    @Column(name = "lecture_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "module")
    private CourseModule module;
}
