package com.example.backend.PersonTests;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.PersonMainInfoDTO;
import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.OTPRepository;
import com.example.backend.Person.repository.PersonRepository;
import com.example.backend.Services.JwtService;
import com.example.backend.Person.service.PersonService;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.exceptions.exceptions.DataNotFoundException;
import com.example.backend.exceptions.exceptions.LoginDataNotValidException;
import jakarta.servlet.http.Cookie;
import jakarta.mail.MessagingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    PersonRepository pr;
    @Autowired
    OTPRepository opr;

    @Test
    void test_save_person() {
        Person person = new Person("ali","amr","ahmed@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertNotNull(pr.findByEmail("ahmed@gmail.com"));
    }

    @Test
    void test_save_person_2() {
        Person person = new Person("Mahmoud","amr","goda123@gmail.com","12345679","2018-11-2","photo7.jpg");
        ps.savePerson(person);
        assertNotNull(pr.findByEmail("goda123@gmail.com"));
        assertNull(pr.findByEmail("ahmedx@gmail.com"));
    }

    @Test
    void test_one_parameter_sendOTP() throws MessagingException {
        ps.sendOTP("yahya912azzam@gmail.com");
        assertNotNull(opr.findOTPByEmail("yahya912azzam@gmail.com"));
        assertNull(opr.findOTPByEmail("yahya912azzam@yahoo.com"));
    }

    @Test
    void test_two_parameter_sendOTP() throws MessagingException {
        ps.sendOTP("yahya912azzam@gmail.com", "123456");
        assertNotNull(opr.findOTPByEmail("yahya912azzam@gmail.com"));
        assertNull(opr.findOTPByEmail("yahya912azzam@yahoo.com"));
    }

    @Test
    void login_1() {
        Person person = new Person("ali","amr","hesham213@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertNotNull(ps.login(null, "hesham213@gmail.com","12345679"));
    }
    @Test
    void login_2() {
        // password not true
        Person person = new Person("ali","amr","ali@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.login(null,"aliamr@gmail.com","aaaaaaaa"));
    }
    @Test
    void login_3() {
        // email not true, not found
        Person person = new Person("ali","amr","alia@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.login(null,"M@gmail.com","12345679"));
    }

    @Test
    void test_convert_to_DTOs() throws JSONException {
        Person person = new Person("ali","amr","aliam@gmail.com","12345679","2020-11-12","photo0.jpg");
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
    void test_cookie() {
        Cookie cookie = ps.createSessionCookie("try");
        assertEquals("try", cookie.getValue());
    }

    @Test
    void test_delete_cookie() {
        Cookie cookie = ps.deleteCookie();
        assertEquals("qcademy", cookie.getName());
        assertEquals(0, cookie.getMaxAge());
    }

    @Test
    void test_validate_otp() {
        SignUpDTO dto = new SignUpDTO("first", "last", "email@domain.com", "password", "1-1-1111");
        assertThrowsExactly(DataNotFoundException.class, () -> ps.validateOTP(dto));
    }

//    @Test
//    void test_authenticator() {
//        JwtService auth = new JwtService(jwtSecret);
//        Person person = new Person("ali","amr","aliam7@gmail.com","12345679","2020-11-12","photo0.jpg");
//        String token = auth.createToken(person, true, true);
//        assertNotEquals(auth.createToken(person, true, false), token);
//    }

    @Test
    void test_use_google() {
        assertThrows(HttpClientErrorException.class, () -> ps.signInUsingGoogle(null, "ss"));
        assertThrows(HttpClientErrorException.class, () -> ps.getGoogleObject("ss"));
    }
}