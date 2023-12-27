package com.example.backend.course.repository;

import com.example.backend.course.model.SolvedAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolvedAssignmentRepository extends JpaRepository<SolvedAssignment, Integer> {
}
