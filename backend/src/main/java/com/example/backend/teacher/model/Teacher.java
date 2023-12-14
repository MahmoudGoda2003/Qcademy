package com.example.backend.teacher.model;

import com.example.backend.course.model.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    private List<Course> courses;

    public Teacher(Long userId) {
        this.userId = userId;
    }
}
