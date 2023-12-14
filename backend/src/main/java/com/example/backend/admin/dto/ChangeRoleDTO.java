package com.example.backend.admin.dto;

import com.example.backend.person.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleDTO {
    @NotBlank(message = "UserId cannot be blank")
    private Long userId;

    @NotBlank(message = "Status cannot be blank")
    private boolean status;
}