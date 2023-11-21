package com.example.backend.PersonTests;

import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.PersonRepository;
import com.example.backend.Person.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    PersonRepository pr;

    @Test
    void test_save_person() {
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        ps.savePerson(person);
        Assertions.assertNotNull(pr.findByEmail("aliamr@gmail.com"));
    }

    @Test
    void test_save_person_2() {
        Person person = new  Person("Mahmoud","amr","Mahmoudamr@gmail.com","12345679","2018-11-2","photo7.jpg");
        ps.savePerson(person);
        Assertions.assertNotNull(pr.findByEmail("Mahmoudamr@gmail.com"));
        Assertions.assertNull(pr.findByEmail("aliamr@gmail.com"));
    }

    @Test
    void validatePasswordByEmail() {
        Assertions.assertTrue(ps.validatePasswordByEmail("aliamr@gmail.com","12345679"));
    }
    @Test
    void validatePasswordByEmail_1() {
        // password not true
        Assertions.assertFalse(ps.validatePasswordByEmail("Mahmoudamr@gmail.com","aaaaaaaa"));
    }
    @Test
    void validatePasswordByEmail_2() {
        // email not true, not found
        Assertions.assertFalse(ps.validatePasswordByEmail("ahmedMohamed@gmail.com","aaaaaaaa"));
    }
}