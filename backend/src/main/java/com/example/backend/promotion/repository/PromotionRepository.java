package com.example.backend.promotion.repository;

import com.example.backend.promotion.model.Promotion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    boolean existsPromotionByUserId(Long userId);


    void deletePromotionByUserId(Long userId);

    Promotion findPromotionByUserId(Long userId);
}
