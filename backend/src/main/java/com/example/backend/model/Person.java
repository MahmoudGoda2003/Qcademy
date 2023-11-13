package com.example.backend.model;

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
    @Column(name = "password", nullable = false)
    @Getter(AccessLevel.NONE)
    private String password;
    @Column(name = "date_of_birth", nullable = false)
    private String DOB;
    @Column(name = "photo_link")
    private String photoLink;

    public Person(String firstName, String lastName, String email, String password, String DOB, String photoLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
        this.photoLink = photoLink;
    }
}
