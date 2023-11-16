package com.example.backend.PersonTests;

import com.example.backend.Person.model.Person;
import com.example.backend.Person.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    PersonService ps;

    @Test
    void test_save(){
        Boolean person = ps.savePerson("Mahmoud","amr","Mahmoudamr@gmail.com","12345679","2018-11-2","photo7.jpg");
        Assertions.assertTrue(person);
    }
    @Test
    void test_save_person() {
        Person person = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        Assertions.assertTrue(ps.savePerson(person));
    }

    @Test
    void test_save_person_2() {
        // test to save an email is in the database
        Boolean person = ps.savePerson("Mahmoud","amr","Mahmoudamr@gmail.com","12345679","2018-11-2","photo7.jpg");
        Assertions.assertFalse(person);
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