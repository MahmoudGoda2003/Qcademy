package com.example.backend.course.repository;

import com.example.backend.course.courseModule.model.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseModuleRepository extends JpaRepository<CourseModule, Integer> {
}
