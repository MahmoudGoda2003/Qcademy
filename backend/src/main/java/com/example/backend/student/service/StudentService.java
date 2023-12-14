package com.example.backend.student.service;

import com.example.backend.person.model.Role;
import com.example.backend.promotion.service.PromotionService;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final PromotionService promotionService;
    private final StudentRepository studentRepository;

    public StudentService(PromotionService promotionService, StudentRepository studentRepository) {
        this.promotionService = promotionService;
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Long userId){
        Student student = new Student(userId);
        this.studentRepository.save(student);
    }
    public ResponseEntity<String> requestPromotion(){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(userId);
        promotionService.requestPromotion(userId, Role.TEACHER);
        return new ResponseEntity<>("Promotion request successfully", HttpStatus.CREATED);
    }


}
