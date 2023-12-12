package com.example.backend.course.repository;

import com.example.backend.course.model.Assigment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigmentRepository extends JpaRepository<Assigment, Integer> {
}
