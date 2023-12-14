package com.example.backend.course.service;

import com.example.backend.course.assigment.model.Assignment;
import com.example.backend.course.assigment.repository.AssigmentRepository;
import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssigmentService {
    private final AssigmentRepository assigmentRepository;
    @Autowired
    public AssigmentService(AssigmentRepository assigmentRepository) {
        this.assigmentRepository = assigmentRepository;
    }

    public Assignment createAssigment(AssignmentDTO assigmentDTO, CourseModule courseModule) {
        Assignment assigment = Assignment.convert(assigmentDTO);
        assigment.setModule(courseModule);
        return assigmentRepository.save(assigment);
    }
}
