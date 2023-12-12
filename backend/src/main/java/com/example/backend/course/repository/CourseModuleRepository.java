package com.example.backend.course.repository;

import com.example.backend.course.model.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Integer> {
}
