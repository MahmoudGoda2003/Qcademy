package com.example.backend.teacher.repository;

import com.example.backend.course.model.Course;
import com.example.backend.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByUserId(Long id);

    Teacher getByUserId(Long id);
}
