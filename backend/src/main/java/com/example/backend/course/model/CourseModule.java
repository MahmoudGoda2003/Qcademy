package com.example.backend.course.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "Module_data")
public class CourseModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "week_number")
    private int Number;

    @Column(name = "publish_date", nullable = false)
    private String publishDate;

    @Column(name = "slides")
    @ElementCollection
    private ArrayList<String> slidesURL;

    @Column(name = "quiz")
    private String quizURL;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private ArrayList<Lecture> lecture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private ArrayList<Assigment> assigment;
}