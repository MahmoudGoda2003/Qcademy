package com.example.backend.course.lecture.model;


import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.LectureDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@Getter
@Setter
@Table(name = "Lecture_data")
public class Lecture {

    @Id
    @Column(name = "lecture_number")
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short number;

    @Column(name = "lecture_url", nullable = false)
    private String videoURL;

    @Column(name = "lecture_name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "week_number")
    private CourseModule module;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static Lecture convert(LectureDTO lectureDTO) {
        return modelMapper.map(lectureDTO, Lecture.class);
    }
}
