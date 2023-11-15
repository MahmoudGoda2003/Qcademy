package com.example.backend.Person.DTO;

import com.example.backend.Person.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
