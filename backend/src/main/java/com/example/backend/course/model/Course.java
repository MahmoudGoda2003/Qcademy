package com.example.backend.course.model;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.student.model.Student;
import com.example.backend.teacher.model.Teacher;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Courses_Data")
public class Course {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "tags")
    @ElementCollection
    private List<String> tags;
    @Column(name = "photo_link")
    private String photoLink;
    @Column(name = "total_rate")
    private int totalRate = 0;
    @Column(name = "number_of_rates")
    private int numberOfRates = 0;
    @Column(name = "estimated_time")
    private short estimatedTime;
    @Column(name = "Start_date")
    private String startDate;
    @Column(name = "teacherName")
    private String teacherName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseModule> modules;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    private List<Student> students;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public static Course convert(CourseMainInfoDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

}
