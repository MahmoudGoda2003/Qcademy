package com.example.backend.PersonTests;

import com.example.backend.Person.DTO.PersonInfoDTO;
import com.example.backend.Person.DTO.PersonMainInfoDTO;
import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.OTPRepository;
import com.example.backend.Person.repository.PersonRepository;
import com.example.backend.Person.service.PersonService;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.exceptions.exceptions.LoginDataNotValidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    PersonRepository pr;
    @Autowired
    OTPRepository opr;

    @Test
    void test_save_person() {
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertNotNull(pr.findByEmail("aliamr@gmail.com"));
    }

    @Test
    void test_save_person_2() {
        Person person = new Person("Mahmoud","amr","Mahmoudamr@gmail.com","12345679","2018-11-2","photo7.jpg");
        ps.savePerson(person);
        assertNotNull(pr.findByEmail("Mahmoudamr@gmail.com"));
        assertNull(pr.findByEmail("aliamr@gmail.com"));
    }

    @Test
    void test_one_parameter_sendOTP() {
        ps.sendOTP("yahya912azzam@gmail.com");
        assertNotNull(opr.findOTPByEmail("yahya912azzam@gmail.com"));
        assertNull(opr.findOTPByEmail("yahya912azzam@yahoo.com"));
    }

    @Test
    void test_two_parameter_sendOTP() {
        ps.sendOTP("yahya912azzam@gmail.com", "123456");
        assertNotNull(opr.findOTPByEmail("yahya912azzam@gmail.com"));
        assertNull(opr.findOTPByEmail("yahya912azzam@yahoo.com"));
    }

    @Test
    void validatePasswordByEmail() {
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertNotNull(ps.validatePasswordByEmail("aliamr@gmail.com","12345679"));
    }
    @Test
    void validatePasswordByEmail_1() {
        // password not true
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.validatePasswordByEmail("aliamr@gmail.com","aaaaaaaa"));
    }
    @Test
    void validatePasswordByEmail_2() {
        // email not true, not found
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.validatePasswordByEmail("M@gmail.com","12345679"));
    }

    @Test
    void test_convert_to_DTOs() {
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        person = pr.findByEmail("aliamr@gmail.com");
        assertNotNull(person);
        PersonInfoDTO personInfoDTO = PersonInfoDTO.convert(person);
        assertNotNull(personInfoDTO);
        PersonMainInfoDTO personMainInfoDTO = PersonMainInfoDTO.convert(person);
        assertEquals(personMainInfoDTO.getFirstName(), person.getFirstName());
    }
}