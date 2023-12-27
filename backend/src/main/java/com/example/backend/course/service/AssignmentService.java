package com.example.backend.course.service;

import com.example.backend.course.dto.SolvedAssignmentDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.SolvedAssignment;
import com.example.backend.course.repository.AssignmentRepository;
import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.repository.SolvedAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    private final AssignmentRepository assigmentRepository;
    private final SolvedAssignmentRepository solvedAssignmentRepository;
    @Autowired
    public AssignmentService(AssignmentRepository assigmentRepository, SolvedAssignmentRepository solvedAssignmentRepository) {
        this.assigmentRepository = assigmentRepository;
        this.solvedAssignmentRepository = solvedAssignmentRepository;
    }

    public Assignment createAssignment(AssignmentDTO assigmentDTO) {
        Assignment assigment = Assignment.convert(assigmentDTO);
        return assigmentRepository.save(assigment);
    }

    public SolvedAssignment submitAssignment(SolvedAssignmentDTO solvedAssignmentDTO) {
        SolvedAssignment solvedAssignment = SolvedAssignment.convert(solvedAssignmentDTO);
        return solvedAssignmentRepository.save(solvedAssignment);
    }

}
