package com.example.backend.course.service;

import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.SolvedAssignment;
import com.example.backend.course.repository.AssignmentRepository;
import com.example.backend.course.repository.SolvedAssignmentRepository;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SolvedAssignmentService {
    private final SolvedAssignmentRepository solvedAssignmentRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    @Transactional
    public void saveAssignmentSolution(String link, Long userId, Integer assignmentNumber){
        Student student = studentRepository.getByUserId(userId);
        Assignment assignment = assignmentRepository.getAssignmentByNumber(assignmentNumber);

        SolvedAssignment solvedAssignment = new Solved
    }
}
