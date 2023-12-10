package com.example.backend.PersonTests.DTO;

import com.example.backend.person.dto.PersonInfoDTO;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.model.Person;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonInfo {

    @Test
    public void PersonMainInfoConvert() {
        Person person = new Person("Yahya", "Azzam", "test1@gmail.com", "test", "1-2-1999", "photo.jpg");
        person.setBio("test bio");
        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);
        assertEquals(personInfoDTO.getFirstName(), person.getFirstName());
        assertEquals(personInfoDTO.getLastName(), person.getLastName());
        assertEquals(personInfoDTO.getEmail(), person.getEmail());
        assertEquals(personInfoDTO.getPhotoLink(), person.getPhotoLink());
        assertEquals(personInfoDTO.getBio(), person.getBio());
    }
}
