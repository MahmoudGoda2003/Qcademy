package com.example.backend.course.lecture.model;


import com.example.backend.course.courseModule.model.CourseModule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Lecture_data")
@IdClass(LectureId.class)
public class Lecture {

    @Id
    @Column(name = "lecture_number")
    private short number;

    @Column(name = "lecture_url", nullable = false)
    private String videoURL;

    @Column(name = "lecture_name", nullable = false)
    private String name;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "module_course_id", referencedColumnName = "course_id"),
            @JoinColumn(name = "module_week_number", referencedColumnName = "week_number")
    })
    private CourseModule module;
}
