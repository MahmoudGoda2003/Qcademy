package com.example.backend.course.courseModule.model;

import com.example.backend.course.assigment.model.Assigment;
import com.example.backend.course.model.Course;
import com.example.backend.course.lecture.model.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "Module_data")
@IdClass(CourseModuleId.class)
public class CourseModule {

    @Id
    @Column(name = "week_number")
    private int weekNumber;
    @Column(name = "publish_date", nullable = false)
    private String publishDate;

    @Column(name = "slides")
    @ElementCollection
    private ArrayList<String> slidesURL;

    @Column(name = "quiz")
    private String quizURL;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private ArrayList<Lecture> lecture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private ArrayList<Assigment> assigment;
}
