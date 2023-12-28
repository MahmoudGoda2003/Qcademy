package com.example.backend.promotion.dto;

import com.example.backend.promotion.model.Promotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    private static final ModelMapper modelMapper = new ModelMapper();
    private Long userId;
    private String userName;
    private String requestedRole;
    private String userImage;

    public static PromotionDTO convert(Promotion promotion) {
        return modelMapper.map(promotion, PromotionDTO.class);
    }
}
