package com.example.backend.course.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Module_data")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "moduleId")
    private int moduleId;

    @Column(name = "publish_date")
    private String publishDate;

    @Column(name = "lecture")
    private String lectureURL;

    @Column(name = "slides")
    private String slidesURL;

    @Column(name = "assignment")
    private String assignmentURL;

    @Column(name = "quiz")
    private String quizURL;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

}