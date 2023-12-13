package com.example.backend.course.lecture.repository;

import com.example.backend.course.lecture.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Short> {
}