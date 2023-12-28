package com.example.backend.promotion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.backend.person.model.Role;
import com.example.backend.promotion.model.Promotion;
import org.junit.jupiter.api.Test;

class PromotionDTOTest {

  @Test
  void testConvert() {

    Promotion promotion = new Promotion();
    promotion.setRole(Role.STUDENT);
    promotion.setUserId(1L);

    assertEquals(1L, PromotionDTO.convert(promotion).getUserId().longValue());
  }

  @Test
  void testConvert2() {

    Promotion promotion = new Promotion(1L, Role.STUDENT);
    promotion.setRole(Role.STUDENT);
    promotion.setUserId(1L);

    assertEquals(1L, PromotionDTO.convert(promotion).getUserId().longValue());
  }

  @Test
  void testNewPromotionDTO() {

    PromotionDTO actualPromotionDTO = new PromotionDTO(1L, "janedoe", Role.STUDENT, "User Image");

    assertEquals("STUDENT", actualPromotionDTO.getRequestedRole());
    assertEquals("User Image", actualPromotionDTO.getUserImage());
    assertEquals("janedoe", actualPromotionDTO.getUserName());
    assertEquals(1L, actualPromotionDTO.getUserId().longValue());
  }

  @Test
  void testNewPromotionDTO2() {

    PromotionDTO actualPromotionDTO = new PromotionDTO(1L, "janedoe", Role.ADMIN, "User Image");

    assertEquals("ADMIN", actualPromotionDTO.getRequestedRole());
    assertEquals("User Image", actualPromotionDTO.getUserImage());
    assertEquals("janedoe", actualPromotionDTO.getUserName());
    assertEquals(1L, actualPromotionDTO.getUserId().longValue());
  }


  @Test
  void testNewPromotionDTO3() {

    PromotionDTO actualPromotionDTO = new PromotionDTO(1L, "janedoe", Role.TEACHER, "User Image");

    assertEquals("TEACHER", actualPromotionDTO.getRequestedRole());
    assertEquals("User Image", actualPromotionDTO.getUserImage());
    assertEquals("janedoe", actualPromotionDTO.getUserName());
    assertEquals(1L, actualPromotionDTO.getUserId().longValue());
  }
}
