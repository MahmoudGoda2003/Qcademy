package com.example.backend.course.service;

import com.example.backend.course.assignment.model.Assignment;
import com.example.backend.course.assignment.repository.AssignmentRepository;
import com.example.backend.course.dto.AssignmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    private final AssignmentRepository assigmentRepository;
    @Autowired
    public AssignmentService(AssignmentRepository assigmentRepository) {
        this.assigmentRepository = assigmentRepository;
    }

    public Assignment createAssignment(AssignmentDTO assigmentDTO) {
        Assignment assigment = Assignment.convert(assigmentDTO);
        return assigmentRepository.save(assigment);
    }
}
