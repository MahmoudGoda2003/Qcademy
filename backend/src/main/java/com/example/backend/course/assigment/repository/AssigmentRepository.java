package com.example.backend.course.assigment.repository;

import com.example.backend.course.assigment.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigmentRepository extends JpaRepository<Assignment, Integer> {
}
