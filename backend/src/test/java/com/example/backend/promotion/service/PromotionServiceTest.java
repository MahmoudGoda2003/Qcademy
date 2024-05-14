package com.example.backend.promotion.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.backend.exceptions.exception.PromotionRequestExistException;
import com.example.backend.person.model.Role;
import com.example.backend.promotion.dto.PromotionDTO;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.repository.PromotionRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PromotionService.class})
@ExtendWith(SpringExtension.class)
class PromotionServiceTest {
    @MockBean
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionService promotionService;

    @Test
    void testRequestPromotion() {

        when(promotionRepository.existsPromotionByUserId(Mockito.<Long>any())).thenReturn(true);

        assertThrows(PromotionRequestExistException.class, () -> promotionService.requestPromotion(1L, Role.STUDENT));
        verify(promotionRepository).existsPromotionByUserId(Mockito.<Long>any());
    }

    @Test
    void testRequestPromotion2() {

        Promotion promotion = new Promotion();
        promotion.setRole(Role.STUDENT);
        promotion.setUserId(1L);
        when(promotionRepository.existsPromotionByUserId(Mockito.<Long>any())).thenReturn(false);
        when(promotionRepository.save(Mockito.<Promotion>any())).thenReturn(promotion);

        promotionService.requestPromotion(1L, Role.STUDENT);

        verify(promotionRepository).existsPromotionByUserId(Mockito.<Long>any());
        verify(promotionRepository).save(Mockito.<Promotion>any());
    }

    @Test
    void testRequestPromotion3() {

        when(promotionRepository.existsPromotionByUserId(Mockito.<Long>any())).thenReturn(true);

        assertThrows(PromotionRequestExistException.class, () -> promotionService.requestPromotion(1L, Role.ADMIN));
        verify(promotionRepository).existsPromotionByUserId(Mockito.<Long>any());
    }

    @Test
    void testRequestPromotion4() {

        when(promotionRepository.existsPromotionByUserId(Mockito.<Long>any())).thenReturn(true);

        assertThrows(PromotionRequestExistException.class, () -> promotionService.requestPromotion(1L, Role.TEACHER));
        verify(promotionRepository).existsPromotionByUserId(Mockito.<Long>any());
    }

    @Test
    void testRequestPromotion5() {

        when(promotionRepository.existsPromotionByUserId(Mockito.<Long>any()))
                .thenThrow(new PromotionRequestExistException());

        assertThrows(PromotionRequestExistException.class, () -> promotionService.requestPromotion(1L, Role.STUDENT));
        verify(promotionRepository).existsPromotionByUserId(Mockito.<Long>any());
    }

    @Test
    void testGetAndDeletePromotion() {

        Promotion promotion = new Promotion();
        promotion.setRole(Role.STUDENT);
        promotion.setUserId(1L);
        when(promotionRepository.findPromotionByUserId(Mockito.<Long>any())).thenReturn(promotion);
        doNothing().when(promotionRepository).deletePromotionByUserId(Mockito.<Long>any());

        Promotion actualAndDeletePromotion = promotionService.getAndDeletePromotion(1L);

        verify(promotionRepository).deletePromotionByUserId(Mockito.<Long>any());
        verify(promotionRepository).findPromotionByUserId(Mockito.<Long>any());
        assertSame(promotion, actualAndDeletePromotion);
    }

    @Test
    void testGetAndDeletePromotion2() {
        // Arrange
        when(promotionRepository.findPromotionByUserId(Mockito.<Long>any()))
                .thenThrow(new PromotionRequestExistException());

        assertThrows(PromotionRequestExistException.class, () -> promotionService.getAndDeletePromotion(1L));
        verify(promotionRepository).findPromotionByUserId(Mockito.<Long>any());
    }

    @Test
    void testGetPromotionRequests() {

        ArrayList<PromotionDTO> promotionDTOList = new ArrayList<>();
        when(promotionRepository.getAllPromotions()).thenReturn(promotionDTOList);

        List<PromotionDTO> actualPromotionRequests = promotionService.getPromotionRequests();

        verify(promotionRepository).getAllPromotions();
        assertTrue(actualPromotionRequests.isEmpty());
        assertSame(promotionDTOList, actualPromotionRequests);
    }

    @Test
    void testGetPromotionRequests2() {

        when(promotionRepository.getAllPromotions()).thenThrow(new PromotionRequestExistException());

        assertThrows(PromotionRequestExistException.class, () -> promotionService.getPromotionRequests());
        verify(promotionRepository).getAllPromotions();
    }
}
