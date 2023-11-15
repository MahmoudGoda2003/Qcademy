package com.example.backend.Person.model;

import com.example.backend.Person.DTO.SignUpDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Persons_Data")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "e_mail", nullable = false, unique = true)
    private String email;
    @Column(name = "encrypted_password", nullable = false)
    private String EncryptedPassword;
    @Column(name = "date_of_birth", nullable = false)
    private String DOB;
    @Column(name = "photo_link")
    private String photoLink;

    public Person(String firstName, String lastName, String email, String encryptedPassword, String DOB, String photoLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.EncryptedPassword = encryptedPassword;
        this.DOB = DOB;
        this.photoLink = photoLink;
    }

    public Person(SignUpDTO signUpDTO) {
        this.firstName = signUpDTO.getFirstName();
        this.lastName = signUpDTO.getLastName();
        this.email = signUpDTO.getEmail();
        this.EncryptedPassword = signUpDTO.getPassword();
        this.DOB = signUpDTO.getDOB();
    }
}
