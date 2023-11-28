package com.example.backend.PersonTests;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.PersonMainInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DtosTest {

    @Test
    void testPersonInfoDTOConversion() {
        Person person = createPerson();
        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);

        assertPersonInfoDTOFields(person, personInfoDTO);
    }

    @Test
    void testPersonInfoDTOToString() {
        PersonInfoDTO personInfoDTO = createPersonInfoDTO();

        assertPersonInfoDTOToString(personInfoDTO);
    }

    @Test
    void testPersonMainInfoDTOConversion() {
        Person person = createPerson();
        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);

        assertPersonMainInfoDTOFields(person, personMainInfoDTO);
    }

    @Test
    void testPersonMainInfoDTOToString() {
        PersonMainInfoDTO personMainInfoDTO = createPersonMainInfoDTO();

        assertPersonMainInfoDTOToString(personMainInfoDTO);
    }

    @Test
    void testSignUpDTOConstructorAndGetters() {
        SignUpDTO signUpDTO = createSignUpDTO();

        assertSignUpDTOFields(signUpDTO);
    }

    @Test
    void testSignUpDTOSetters() {
        SignUpDTO signUpDTO = createSignUpDTO();

        signUpDTO.setFirstName("Jane");
        signUpDTO.setPassword("newSecurePassword");

        assertSignUpDTOFieldsAfterSetters(signUpDTO);
    }

    // Helper methods...

    private Person createPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setPhotoLink("https://example.com/photo.jpg");
        person.setDateOfBirth("1990-01-01");
        return person;
    }

    private PersonInfoDTO createPersonInfoDTO() {
        Person person = createPerson();
        return PersonInfoDTO.convert(person);
    }

    private PersonMainInfoDTO createPersonMainInfoDTO() {
        Person person = createPerson();
        return PersonMainInfoDTO.convert(person);
    }

    private SignUpDTO createSignUpDTO() {
        return new SignUpDTO("John", "Doe", "john.doe@example.com", "securePassword", "1990-01-01");
    }

    // Assertion methods...

    void assertPersonInfoDTOFields(Person person, PersonInfoDTO personInfoDTO) {
        assertEquals(person.getId(), personInfoDTO.getId());
        assertEquals(person.getFirstName(), personInfoDTO.getFirstName());
        assertEquals(person.getLastName(), personInfoDTO.getLastName());
        assertEquals(person.getEmail(), personInfoDTO.getEmail());
        assertEquals(person.getPhotoLink(), personInfoDTO.getPhotoLink());
        assertEquals(person.getDateOfBirth(), personInfoDTO.getDateOfBirth());
    }

    void assertPersonInfoDTOToString(PersonInfoDTO personInfoDTO) {
        String expectedToString = "PersonInfoDTO(id=null, firstName=John, lastName=Doe, email=john.doe@example.com, " +
                "photoLink=https://example.com/photo.jpg, dateOfBirth=1990-01-01)";
        assertEquals(expectedToString, personInfoDTO.toString());
    }

    void assertPersonMainInfoDTOFields(Person person, PersonMainInfoDTO personMainInfoDTO) {
        assertEquals(person.getId(), personMainInfoDTO.getId());
        assertEquals(person.getFirstName(), personMainInfoDTO.getFirstName());
        assertEquals(person.getLastName(), personMainInfoDTO.getLastName());
    }

    void assertPersonMainInfoDTOToString(PersonMainInfoDTO personMainInfoDTO) {
        String expectedToString = "PersonMainInfoDTO(id=null, firstName=John, lastName=Doe)";
        assertEquals(expectedToString, personMainInfoDTO.toString());
    }

    void assertSignUpDTOFields(SignUpDTO signUpDTO) {
        assertEquals("John", signUpDTO.getFirstName());
        assertEquals("Doe", signUpDTO.getLastName());
        assertEquals("john.doe@example.com", signUpDTO.getEmail());
        assertEquals("securePassword", signUpDTO.getPassword());
        assertEquals("1990-01-01", signUpDTO.getDateOfBirth());
    }

    void assertSignUpDTOFieldsAfterSetters(SignUpDTO signUpDTO) {
        assertEquals("Jane", signUpDTO.getFirstName());
        assertEquals("newSecurePassword", signUpDTO.getPassword());
    }
}
