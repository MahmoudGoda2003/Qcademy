package com.example.backend.promotion.repository;

import com.example.backend.promotion.dto.PromotionDTO;
import com.example.backend.promotion.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    boolean existsPromotionByUserId(Long userId);


    void deletePromotionByUserId(Long userId);

    Promotion findPromotionByUserId(Long userId);

    @Query("SELECT new com.example.backend.promotion.dto.PromotionDTO(p.id, CONCAT(p.firstName, ' ', p.lastName), pr.role, p.photoLink) FROM Person p JOIN Promotion pr ON p.id = pr.userId")
    List<PromotionDTO> getAllPromotions();
}
