package com.example.backend.course.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Courses_Data")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "courseId")
    private int Id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    @ElementCollection
    private ArrayList<String> tags;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "rating")
    private float rating;

    @Column(name = "estimated_time")
    private short estimatedTime;

    @Column(name = "Start_date")
    private String startDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private ArrayList<CourseModule> module;
}
