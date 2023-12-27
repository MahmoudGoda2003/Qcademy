package com.example.backend.course.service;

import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.course.repository.AssignmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assigmentRepository;

    public Assignment createAssignment(AssignmentDTO assigmentDTO) {
        Assignment assigment = Assignment.convert(assigmentDTO);
        return assigmentRepository.save(assigment);
    }
}
