package com.example.backend.course.model;

import com.example.backend.course.dto.CourseModuleDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "Module_data")
public class CourseModule {

    private static final ModelMapper modelMapper = new ModelMapper();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "week_number")
    private int weekNumber;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "slides")
    @ElementCollection
    private List<String> slidesSets;
    @Column(name = "quiz")
    @ElementCollection
    private List<String> quizzes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Assignment> assignments = new ArrayList<>();

    public static CourseModule convert(CourseModuleDTO courseModuleDTO) {
        return modelMapper.map(courseModuleDTO, CourseModule.class);
    }
}
