package com.example.backend.promotion.service;


import com.example.backend.exceptions.exception.PromotionRequestExistException;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.promotion.dto.PromotionDTO;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public void requestPromotion(Long userId, Role requestedRole) {
        if (this.promotionRepository.existsPromotionByUserId(userId))
            throw new PromotionRequestExistException();
        Promotion promotion = new Promotion(userId, requestedRole);
        this.promotionRepository.save(promotion);
    }


    public Promotion getAndDeletePromotion(Long userId) {
        Promotion promotion = this.promotionRepository.findPromotionByUserId(userId);
        this.promotionRepository.deletePromotionByUserId(userId);
        return promotion;
    }


    public List<PromotionDTO> getPromotionRequests() {
        List<Promotion> promotionRequests = this.promotionRepository.findAll();

        List<PromotionDTO> promotionDTOS = new ArrayList<>();
        for (Promotion promotion : promotionRequests) {
            promotionDTOS.add(PromotionDTO.convert(promotion));
        }
        return promotionDTOS;
    }
}
