package com.example.backend.course.assigment.model;

import com.example.backend.course.courseModule.model.CourseModule;
import jakarta.persistence.*;

import java.io.Serializable;


public class AssigmentId implements Serializable {

    private CourseModule module;

    private short number;
}