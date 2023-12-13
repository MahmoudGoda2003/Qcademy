package com.example.backend.teacher.service;

import com.example.backend.person.model.Role;
import com.example.backend.promotion.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final PromotionService promotionService;

    public TeacherService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    public ResponseEntity<String> requestPromotion(){
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(userId);
        promotionService.requestPromotion(userId, Role.ADMIN);
        return new ResponseEntity<>("Promotion request successfully", HttpStatus.CREATED);
    }
}
