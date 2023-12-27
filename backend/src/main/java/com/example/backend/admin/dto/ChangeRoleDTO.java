package com.example.backend.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleDTO {
    @NotBlank(message = "UserId cannot be blank")
    private Long userId;

    @NotBlank(message = "Status cannot be blank")
    private boolean status;
}
