package com.example.backend.course.lecture.model;

import com.example.backend.course.courseModule.model.CourseModule;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class LectureId implements Serializable {

    @ManyToOne
    private CourseModule module;

    private short number;
}
