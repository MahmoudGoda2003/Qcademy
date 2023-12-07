package com.example.backend.PersonTests;

import com.example.backend.exceptions.exception.LoginDataNotValidException;
import com.example.backend.person.dto.PersonInfoDTO;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.Authenticator;
import com.example.backend.person.service.PersonService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    PersonRepository pr;

    @Test
    void test_save_person() {
        Person person = new Person("ali", "amr", "ahmed@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        ps.savePerson(person);
        assertNotNull(pr.findByEmail("ahmed@gmail.com"));
    }

    @Test
    void test_save_person_2() {
        Person person = new Person("Mahmoud", "amr", "goda123@gmail.com", "12345679", "2018-11-2", "photo7.jpg");
        ps.savePerson(person);
        assertNotNull(pr.findByEmail("goda123@gmail.com"));
        assertNull(pr.findByEmail("ahmedx@gmail.com"));
    }

    @Test
    void login_1() {
        Person person = new Person("ali", "amr", "hesham213@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        ps.savePerson(person);
        assertNotNull(ps.login(null, "hesham213@gmail.com", "12345679"));
    }

    @Test
    void login_2() {
        // password not true
        Person person = new Person("ali", "amr", "ali@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        ps.savePerson(person);
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.login(null, "aliamr@gmail.com", "aaaaaaaa"));
    }

    @Test
    void login_3() {
        // email not true, not found
        Person person = new Person("ali", "amr", "alia@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        ps.savePerson(person);
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.login(null, "M@gmail.com", "12345679"));
    }

    @Test
    void test_convert_to_DTOs() throws JSONException {
        Person person = new Person("ali", "amr", "aliam@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        ps.savePerson(person);
        person = pr.findByEmail("aliam@gmail.com");
        assertNotNull(person);
        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);
        assertNotNull(personInfoDTO);
        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);
        assertEquals(personMainInfoDTO.getFirstName(), person.getFirstName());
        SignUpDTO signUpDTO = new SignUpDTO("first", "last", "email@domain.com", "password", "1-1-1111");
        assertEquals("email@domain.com", signUpDTO.getEmail());
        person = Person.convert(signUpDTO);
        assertEquals(signUpDTO.getDateOfBirth(), person.getDateOfBirth());
        JSONObject jsonObject = new JSONObject("{'given_name' : 'woman', 'family_name' : 'man', 'email' : 'email', 'picture' : 'p', 'id' : 'testing'}");
        person = new Person(jsonObject);
        assertEquals("testing", person.getPassword());
    }

    @Test
    void test_authenticator() {
        Authenticator auth = new Authenticator();
        Person person = new Person("ali", "amr", "aliam7@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        String token = auth.createToken(person, true, true);
        assertNotEquals(auth.createToken(person, true, false), token);
    }

    @Test
    void test_use_google() {
        assertThrows(HttpClientErrorException.class, () -> ps.signInUsingGoogle(null, "ss"));
        assertThrows(HttpClientErrorException.class, () -> ps.getGoogleObject("ss"));
    }
}