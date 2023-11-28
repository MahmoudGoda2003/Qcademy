package com.example.backend.PersonTests;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.PersonMainInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.Person;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DtosTest {

    @Test
    public void testPersonInfoDTOIdAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(null, personInfoDTO.getId());
    }

    @Test
    public void testPersonInfoDTOFirstNameAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(person.getFirstName(), personInfoDTO.getFirstName());
    }

    @Test
    public void testPersonInfoDTOLastNameAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(person.getLastName(), personInfoDTO.getLastName());
    }

    @Test
    public void testPersonInfoDTOEmailAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(person.getEmail(), personInfoDTO.getEmail());
    }

    @Test
    public void testPersonInfoDTOPhotoLinkAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(person.getPhotoLink(), personInfoDTO.getPhotoLink());
    }

    @Test
    public void testPersonInfoDTODateOfBirthAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");

        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertEquals(person.getDateOfBirth(), personInfoDTO.getDateOfBirth());
    }

    @Test
    public void testPersonMainInfoDTOIdAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);

        assertEquals(null, personMainInfoDTO.getId());
    }

    @Test
    public void testPersonMainInfoDTOFirstNameAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);

        assertEquals(person.getFirstName(), personMainInfoDTO.getFirstName());
    }

    @Test
    public void testPersonMainInfoDTOLastNameAssertion() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);

        assertEquals(person.getLastName(), personMainInfoDTO.getLastName());
    }

    @Test
    public void testSignUpDTOFirstNameAssertion() {
        SignUpDTO signUpDTO = new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");

        assertEquals("John", signUpDTO.getFirstName());
    }

    @Test
    public void testSignUpDTOLastNameAssertion() {
        SignUpDTO signUpDTO = new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");

        assertEquals("Doe", signUpDTO.getLastName());
    }

    @Test
    public void testSignUpDTOEmailAssertion() {
        SignUpDTO signUpDTO = new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");

        assertEquals("john.doe@example.com", signUpDTO.getEmail());
    }

    @Test
    public void testSignUpDTOPasswordAssertion() {
        SignUpDTO signUpDTO = new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");

        assertEquals("securePassword", signUpDTO.getPassword());
    }

    @Test
    public void testSignUpDTODateOfBirthAssertion() {
        SignUpDTO signUpDTO = new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");
        
        assertEquals("1990-01-01", signUpDTO.getDateOfBirth());
    }

}
