package com.example.backend.Person.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;

    private String code;

    public SignUpDTO(String firstName, String lastName, String email, String password, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}
