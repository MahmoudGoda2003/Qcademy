package com.example.backend.course.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "Courses_Data")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "number")
    private int number;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tages")
    private String tags;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "rating")
    private float rating;

    @Column(name = "estimated_time")
    private short estimatedTime;

    @Column(name = "Start_date")
    private String startDate;

}
