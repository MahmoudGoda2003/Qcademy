package com.example.backend.course.assigment.model;

import com.example.backend.course.courseModule.model.CourseModule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "Assigment_data")
@IdClass(AssigmentId.class)
public class Assigment {
    @Id
    private short number;

    @Column(name = "assigment_name", nullable = false)
    private String name;

    @Column(name = "assigment_url", nullable = false)
    private String assigmentURL;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assigment")
    private ArrayList<SolvedAssigment> solvedAssigment;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "module_course_id"),
            @JoinColumn(name = "module_week_number")
    })
    private CourseModule module;
}
