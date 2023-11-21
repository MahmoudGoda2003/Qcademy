package com.example.backend.Person.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String DOB;

    private String code;

    public SignUpDTO(String firstName, String lastName, String email, String password, String DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }
}
