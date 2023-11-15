package com.example.backend.Person.DTO;

import com.example.backend.Person.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonMainInfoDTO {

    private Long id;
    private String firstName;
    private String lastName;

    public PersonMainInfoDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonMainInfoDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }
}
