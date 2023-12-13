package com.example.backend.course.model;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.student.model.Student;
import com.example.backend.teacher.model.Teacher;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Courses_Data")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "course_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    @ElementCollection
    private ArrayList<String> tags;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "rating")
    private float rating;

    @Column(name = "estimated_time")
    private short estimatedTime;

    @Column(name = "Start_date")
    private String startDate;

    @Column(name = "teacherName")
    private String teacherName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private ArrayList<CourseModule> module;

    @ManyToMany(mappedBy = "courses")
    private ArrayList<Student> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static Course convert(CourseMainInfoDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

}
