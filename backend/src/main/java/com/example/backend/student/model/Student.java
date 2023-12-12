package com.example.backend.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private Long userId;


    public Student(Long userId) {
        this.userId = userId;
    }
}
