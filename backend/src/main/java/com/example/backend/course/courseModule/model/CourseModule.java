package com.example.backend.course.courseModule.model;

import com.example.backend.course.assignment.model.Assignment;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.lecture.model.Lecture;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;


import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Module_data")
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "week_number")
    private int weekNumber;

    @Column(name = "publish_date", nullable = false)
    private String publishDate;

    @Column(name = "slides")
    @ElementCollection
    private List<String> slidesSets;

    @Column(name = "quiz")
    @ElementCollection
    private List<String> quizzes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static CourseModule convert(CourseModuleDTO courseModuleDTO) {
        return modelMapper.map(courseModuleDTO, CourseModule.class);
    }
}
