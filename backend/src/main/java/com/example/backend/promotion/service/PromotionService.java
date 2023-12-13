package com.example.backend.promotion.service;


import com.example.backend.exceptions.exception.WrongDataEnteredException;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.repository.PromotionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }


    @Transactional
    public void requestPromotion(Long userId, Role requestedRole){
        if(this.promotionRepository.existsPromotionByUserId(userId))
            throw new WrongDataEnteredException("There is already a request by that user");
        Promotion promotion = new Promotion(userId, requestedRole);
        this.promotionRepository.save(promotion);
    }


    @Transactional
    public void deletePromotion(Long userId){
        this.promotionRepository.deletePromotionByUserId(userId);
    }
}
