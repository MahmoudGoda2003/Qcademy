package com.example.backend.Person.DTO;

import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.model.Person;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonMainInfoTest {

    @Test
    public void PersonMainInfoConvert() {
        Person person = new Person("Yahya", "Azzam", "tee2132tstst@gmail.com", "123456789", "1-2-1999", "photo.jpg");
        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);
        assertEquals(personMainInfoDTO.getFirstName(), person.getFirstName());
        assertEquals(personMainInfoDTO.getLastName(), person.getLastName());
        assertEquals(personMainInfoDTO.getEmail(), person.getEmail());
        assertEquals(personMainInfoDTO.getPhotoLink(), person.getPhotoLink());
    }
}
