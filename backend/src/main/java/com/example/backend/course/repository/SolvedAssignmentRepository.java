package com.example.backend.course.repository;

import com.example.backend.course.model.SolvedAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedAssignmentRepository extends JpaRepository<SolvedAssignment, Short> {

}
