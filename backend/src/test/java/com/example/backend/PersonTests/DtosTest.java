package com.example.backend.PersonTests;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.PersonMainInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.Person;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DtosTest {
    @Test
    public void testPersonInfoDTO() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(person.getId(), personInfoDTO.getId());
        assertEquals(person.getFirstName(), personInfoDTO.getFirstName());
        assertEquals(person.getLastName(), personInfoDTO.getLastName());
        assertEquals(person.getEmail(), personInfoDTO.getEmail());
        assertEquals(person.getPhotoLink(), personInfoDTO.getPhotoLink());
        assertEquals(person.getDateOfBirth(), personInfoDTO.getDateOfBirth());

        String expectedToString = "PersonInfoDTO(id=null, firstName=John, lastName=Doe, email=john.doe@example.com, " +
                "photoLink=https://example.com/photo.jpg, dateOfBirth=1990-01-01)";
        assertEquals(expectedToString, personInfoDTO.toString());
    }

    @Test
    public void testPersonMainInfoDTO() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");


        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);

        assertEquals(person.getId(), personMainInfoDTO.getId());
        assertEquals(person.getFirstName(), personMainInfoDTO.getFirstName());
        assertEquals(person.getLastName(), personMainInfoDTO.getLastName());

        String expectedToString = "PersonMainInfoDTO(id=null, firstName=John, lastName=Doe)";
        assertEquals(expectedToString, personMainInfoDTO.toString());
    }

    @Test
    public void testSignUpDTOConstructorAndGetters() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "securePassword";
        String dateOfBirth = "1990-01-01";

        SignUpDTO signUpDTO = new SignUpDTO(firstName, lastName, email, password, dateOfBirth);

        assertEquals(firstName, signUpDTO.getFirstName());
        assertEquals(lastName, signUpDTO.getLastName());
        assertEquals(email, signUpDTO.getEmail());
        assertEquals(password, signUpDTO.getPassword());
        assertEquals(dateOfBirth, signUpDTO.getDateOfBirth());
    }

    @Test
    public void testSignUpDTOSetters() {
        SignUpDTO signUpDTO = new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");

        String newFirstName = "Jane";
        String newPassword = "newSecurePassword";
        signUpDTO.setFirstName(newFirstName);
        signUpDTO.setPassword(newPassword);

        assertEquals(newFirstName, signUpDTO.getFirstName());
        assertEquals(newPassword, signUpDTO.getPassword());
    }

}
