package com.example.backend.person.model;

import com.example.backend.person.dto.SignUpDTO;
import jakarta.persistence.*;
import lombok.*;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "photo_link")
    private String photoLink;
    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role = Role.STUDENT;

    private static ModelMapper modelMapper = new ModelMapper();

    public Person(String firstName, String lastName, String email, String password, String dateOfBirth, String photoLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.photoLink = photoLink;
    }

    public static Person convert(SignUpDTO signUpDTO) {
        return modelMapper.map(signUpDTO, Person.class);
    }

    public Person(JSONObject object) {
        firstName = object.getString("given_name");
        lastName = object.getString("family_name");
        email = object.getString("email");
        photoLink = object.getString("picture");
        password = object.getString("id");
    }
}
