package com.example.backend.course.model;

import com.example.backend.course.dto.AssignmentDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Entity
@Data
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
    private List<SolvedAssignment> solvedAssignment;

    public static Assignment convert(AssignmentDTO assigmentDTO) {
        return modelMapper.map(assigmentDTO, Assignment.class);
    }
}
