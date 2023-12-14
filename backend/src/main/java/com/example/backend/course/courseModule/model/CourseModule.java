package com.example.backend.course.courseModule.model;

import com.example.backend.course.assigment.model.Assignment;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.lecture.model.Lecture;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    private List<String> slidesURL;

    @Column(name = "quiz")
    @ElementCollection
    private List<String> quizzes;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private List<Lecture> lectures;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private List<Assignment> assignments;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static CourseModule convert(CourseModuleDTO courseModuleDTO) {
        CourseModule courseModule =  modelMapper.map(courseModuleDTO, CourseModule.class);
        List<Lecture> lectures = new ArrayList<>();
        for (LectureDTO lectureDTO : courseModuleDTO.getLectures()) {
            Lecture lecture = Lecture.convert(lectureDTO);
            lectures.add(lecture);
        }
        return courseModule;
    }
}
