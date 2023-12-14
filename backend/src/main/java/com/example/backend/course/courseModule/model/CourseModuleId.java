package com.example.backend.course.courseModule.model;

import com.example.backend.course.model.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class CourseModuleId implements Serializable {

    private Course course;

    private int weekNumber;
}
