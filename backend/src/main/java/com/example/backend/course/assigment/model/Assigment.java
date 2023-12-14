package com.example.backend.course.assigment.model;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.AssigmentDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "Assigment_data")
@IdClass(AssigmentId.class)
@ToString
public class Assigment {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assigment_number")
    @Setter(AccessLevel.NONE)
    private short number;
    @Column(name = "assigment_name", nullable = false)
    private String name;
    @Column(name = "assigment_url", nullable = false)
    private String assigmentURL;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignments")
    private ArrayList<SolvedAssigment> solvedAssigment;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name = "module_course_id"), @JoinColumn(name = "module_week_number")})
    private CourseModule module;

    public static Assigment convert(AssigmentDTO assigmentDTO) {
        return modelMapper.map(assigmentDTO, Assigment.class);
    }
}
