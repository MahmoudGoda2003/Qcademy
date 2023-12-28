package com.example.backend.course.repository;

import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    Assignment getAssignmentByNumber(Integer number);
}
