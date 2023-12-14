package com.example.backend.admin.dto;

import com.example.backend.person.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeRoleDTO {
    @NotBlank(message = "UserId cannot be blank")
    private Long userId;

    @NotBlank(message = "Role cannot be blank")
    private Role role;

    @NotBlank(message = "Status cannot be blank")
    private boolean status;
}
