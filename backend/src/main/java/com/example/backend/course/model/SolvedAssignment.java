package com.example.backend.course.model;


import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.SolvedAssignmentDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.student.model.Student;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "SolvedAssignment_data")
@IdClass(SolvedAssignmentId.class)
public class SolvedAssignment {

    @Column(name = "solution_url", nullable = false)
    private String solutionURL;

    @Column(name = "grade")
    private short grade;


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_number")
    private Assignment assignments;


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static SolvedAssignment convert(SolvedAssignmentDTO solvedAssignmentDTO) {
        return modelMapper.map(solvedAssignmentDTO, SolvedAssignment.class);
    }
}
