package com.example.backend.course.repository;

import com.example.backend.course.model.SolvedAssignment;
import com.example.backend.course.model.SolvedAssignmentId;
import com.example.backend.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolvedAssignmentRepository extends JpaRepository<SolvedAssignment, SolvedAssignmentId> {
    @Query("SELECT sa FROM SolvedAssignment sa WHERE sa.student = :student")
    List<SolvedAssignment> findByStudent(@Param("student") Student student);
}
