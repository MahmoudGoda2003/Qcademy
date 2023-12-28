package com.example.backend.course.service;

import com.example.backend.course.dto.SolvedAssignmentDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.SolvedAssignment;
import com.example.backend.course.model.SolvedAssignmentId;
import com.example.backend.course.repository.AssignmentRepository;
import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.repository.SolvedAssignmentRepository;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assigmentRepository;
    private final SolvedAssignmentRepository solvedAssignmentRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;


    public Assignment createAssignment(AssignmentDTO assigmentDTO) {
        Assignment assigment = Assignment.convert(assigmentDTO);
        return assigmentRepository.save(assigment);
    }

    public ResponseEntity<String> submitAssignment(SolvedAssignmentDTO solvedAssignmentDTO) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = studentRepository.getByUserId(userId);
        Assignment assignment = assignmentRepository.getById(solvedAssignmentDTO.getAssignmentNumber());
        SolvedAssignment solvedAssignment = SolvedAssignment.convert(solvedAssignmentDTO);
        solvedAssignment.setStudent(student);
        solvedAssignment.setAssignments(assignment);
        solvedAssignmentRepository.save(solvedAssignment);
        assignment.getSolvedAssignment().add(solvedAssignment);
        assigmentRepository.save(assignment);
        return new ResponseEntity<>(String.format("solution submitted"), HttpStatus.CREATED);
    }

    public ResponseEntity<List<SolvedAssignmentDTO>> getSubmissions(Short assignmentNumber) {
        Assignment assignment = assignmentRepository.getById(assignmentNumber);
        List<SolvedAssignment> solvedAssignments = assignment.getSolvedAssignment();
        List<SolvedAssignmentDTO> submittedSolutions = new ArrayList<>();
        for (SolvedAssignment solvedAssignment : solvedAssignments) {
            submittedSolutions.add(SolvedAssignmentDTO.convert(solvedAssignment));
        }
        return new ResponseEntity<>(submittedSolutions, HttpStatus.CREATED);
    }

    public ResponseEntity<String> setGrade(SolvedAssignmentDTO solvedAssignmentDTO) {
        Student student = studentRepository.getByUserId(solvedAssignmentDTO.getStudentId());
        Assignment assignment = assignmentRepository.getById(solvedAssignmentDTO.getAssignmentNumber());
        SolvedAssignmentId key = new SolvedAssignmentId(assignment, student);
        SolvedAssignment solvedAssignment = solvedAssignmentRepository.getById(key);
        solvedAssignment.setGrade(solvedAssignmentDTO.getGrade());
        solvedAssignmentRepository.save(solvedAssignment);
        return new ResponseEntity<>("submitted grades", HttpStatus.CREATED);
    }

    public ResponseEntity<List<SolvedAssignmentDTO>> getGrades() {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = studentRepository.getByUserId(userId);
        List<SolvedAssignment> solvedAssignments =  solvedAssignmentRepository.findByStudent(student);
        List<SolvedAssignmentDTO> submittedSolutions = new ArrayList<>();
        for (SolvedAssignment solvedAssignment : solvedAssignments) {
            submittedSolutions.add(SolvedAssignmentDTO.convert(solvedAssignment));
        }
        return new ResponseEntity<>(submittedSolutions, HttpStatus.CREATED);
    }
}
