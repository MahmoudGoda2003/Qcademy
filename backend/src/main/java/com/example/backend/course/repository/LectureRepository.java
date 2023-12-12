package com.example.backend.course.repository;

import com.example.backend.course.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Short> {
}
