package com.example.backend.Person.model;

import com.example.backend.Person.DTO.SignUpDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Persons_Data", indexes = {@Index(name = "index_email", columnList = "email", unique = true)})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;
    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;
    @Column(name = "photo_link")
    private String photoLink;

    public Person(String firstName, String lastName, String email, String encryptedPassword, String dateOfBirth, String photoLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.dateOfBirth = dateOfBirth;
        this.photoLink = photoLink;
    }

    public Person(SignUpDTO signUpDTO) {
        this.firstName = signUpDTO.getFirstName();
        this.lastName = signUpDTO.getLastName();
        this.email = signUpDTO.getEmail();
        this.encryptedPassword = signUpDTO.getPassword();
        this.dateOfBirth = signUpDTO.getDateOfBirth();
    }
}
