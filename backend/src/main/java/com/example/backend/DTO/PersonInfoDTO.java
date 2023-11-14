package com.example.backend.DTO;

import com.example.backend.model.Person;

public class PersonInfoDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String photoLink;
    private String DOB;

    public PersonInfoDTO(Long id, String firstName, String lastName, String emailAddress, String photoLink, String DOB) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.photoLink = photoLink;
        this.DOB = DOB;
    }

    public PersonInfoDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.emailAddress = person.getEmail();
        this.photoLink = person.getPhotoLink();
        this.DOB = person.getDOB();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
