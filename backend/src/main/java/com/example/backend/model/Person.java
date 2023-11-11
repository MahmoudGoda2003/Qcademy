package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Persons_Data")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String fristName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "E_mail")
    private String Email;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Date_of_birth")
    private String DOB;

    public Person() {
    }

    public Person(String fristName, String lastName, String email, String password, String DOB) {
        this.fristName = fristName;
        this.lastName = lastName;
        Email = email;
        Password = password;
        this.DOB = DOB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
