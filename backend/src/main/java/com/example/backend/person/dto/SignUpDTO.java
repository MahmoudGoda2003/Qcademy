package com.example.backend.person.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    @NotBlank(message = "First name cannot be Empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be Empty")
    private String lastName;

    @NotBlank(message = "Email cannot be Empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be Empty")
    private String password;

    @NotBlank(message = "Date of birth cannot be Empty")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid date of birth format. Use DD-MM-YYYY")
    private String dateOfBirth;

    private String code;
}
