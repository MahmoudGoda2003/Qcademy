package com.example.backend.course.repository;

import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.SolvedAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolvedAssignmentRepository extends JpaRepository<SolvedAssignment, SolvedAssignmentId> {
    List<SolvedAssignment> findSolvedAssignmentsByAssignment(Assignment assignment);

}
