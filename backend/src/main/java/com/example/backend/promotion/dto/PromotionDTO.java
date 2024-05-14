package com.example.backend.promotion.dto;

import com.example.backend.person.model.Role;
import com.example.backend.promotion.model.Promotion;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class PromotionDTO {
    private static final ModelMapper modelMapper = new ModelMapper();
    private Long userId;
    private String userName;
    private String requestedRole;
    private String userImage;

    public PromotionDTO(Long userId, String userName, Role requestedRole, String userImage) {
        this.userId = userId;
        this.userName = userName;
        this.requestedRole = requestedRole.name();
        this.userImage = userImage;
    }


    public static PromotionDTO convert(Promotion promotion) {
        return modelMapper.map(promotion, PromotionDTO.class);
    }
}
