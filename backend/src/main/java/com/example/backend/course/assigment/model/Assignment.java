package com.example.backend.course.assigment.model;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.AssignmentDTO;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Assignment_data")
public class Assignment {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_number")
    @Setter(AccessLevel.NONE)
    private short number;
    @Column(name = "assignment_name", nullable = false)
    private String name;
    @Column(name = "assignment_url", nullable = false)
    private String assignmentURL;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignments")
    private List<SolvedAssigment> solvedAssignment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "week_number")
    private CourseModule module;

    public static Assignment convert(AssignmentDTO assigmentDTO) {
        return modelMapper.map(assigmentDTO, Assignment.class);
    }
}
