package com.example.backend.student.model;

import com.example.backend.course.model.Course;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    public Student(Long userId) {
        this.userId = userId;
    }
}
