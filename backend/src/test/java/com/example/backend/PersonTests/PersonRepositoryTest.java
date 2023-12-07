package com.example.backend.PersonTests;
import com.example.backend.person.model.Person;
import com.example.backend.person.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class PersonRepositoryTest {
    @Autowired
    PersonRepository pr;

    List<Person> some_data(){
        Person[] person = new Person[6];
        person[0] = new Person("ali","amr","aliamr@gmail.com","12345679","2020-11-12","photo0.jpg");
        person[1] = new Person("ali","ahmed","aliahmed@gmail.com","12345679","2015-11-12","photo1.jpg");
        person[2] = new Person("Mohammed","ahmed","Mohammedahmed@gmail.com","12345679","2006-1-12","photo2.jpg");
        person[3] = new Person("Mohammed","amr","Mohammedamr@gmail.com","12345679","2018-12-1","photo3.jpg");
        person[4] = new Person("amr","ahmed","amrahmed@gmail.com","12345679","2015-6-2","photo4.jpg");
        person[5] = new Person("Mahmoud","Mohammed","MahmoudMohammed@gmail.com","12345679","2009-1-11","photo5.jpg");

        return new ArrayList<>(Arrays.asList(person).subList(0, 6));
    }
    @Test
    void test_save(){
        Person p = new Person("Mahmoud","amr","Mahmoudamr@gmail.com","12345679","2018-11-2","photo7.jpg");
        pr.save(p);
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(1,personsTest.size());
    }
    @Test
    void test_save_all(){
        List<Person> persons = some_data();
        // Save data in the table
        pr.saveAll(persons);
        // Test the number of Elements inserted (saved) in the table
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(6,personsTest.size());
    }
    @Test
    void test_find_all(){
        List<Person> persons = some_data();
        // Save data in the table
        pr.saveAll(persons);
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(6,personsTest.size());
    }
    @Test
    void test_find_by_email(){
        Person p = new Person("Mohammed","ahmed","Mohammedahmed@gmail.com","12345679","2006-1-12","photo2.jpg");
        pr.save(p);
        Person personTest = pr.findByEmail("Mohammedahmed@gmail.com");
        assertEquals("Mohammed",personTest.getFirstName());
    }
    @Test
    void test_get_by_id(){
        Person p = new Person("Mahmoud","ahmed","Mohd@gmail.com","12345679","2006-1-12","photo2.jpg");
        pr.save(p);
        System.out.println(p.getId());
        Person personTest = pr.getOne(p.getId());
        assertEquals("Mahmoud",personTest.getFirstName());
    }

    @Test
    void test_delete_by_id(){
        List<Person> persons = some_data();
        // Save data in the table
        pr.saveAll(persons);
        pr.deleteById(3L);
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(6,personsTest.size());
    }
    @Test
    void test_update_by_email(){
        Person person = new Person("Mohammed","ahmed","Mohammedahmed@gmail.com","12345679","2006-1-12","photo2.jpg");
        pr.save(person);
        Person p = pr.findByEmail("Mohammedahmed@gmail.com");
        Long id = p.getId();
        p.setEmail("MoSalah@gmail.com");
        p.setDateOfBirth("2005-9-14");
        pr.save(p);
        Assertions.assertEquals("MoSalah@gmail.com", pr.getById(id).getEmail());
    }
    @Test
    void test_save_UniqueConstraintViolation() {
        List<Person> persons = some_data();
        // Save data in the table
        pr.saveAll(persons);
        Person p1 = new Person("Ahmed","ali","AhmedAli@gmail.com","12345679","2008-1-1","photo8.jpg");
        Person p2 = new Person("Ahmed","Mohamed","AhmedAli@gmail.com","12345679","2007-1-1","photo9.jpg");
        pr.save(p1);
        Assertions.assertEquals(7,pr.count());


        assertThrows(DataIntegrityViolationException.class, () -> pr.save(p2));
    }
    @Test
    void test_save_NullConstraintViolation() {
        Person nullPerson = new Person();
        nullPerson.setFirstName("Mohamed");
        nullPerson.setFirstName("yousef");
        assertThrows(DataIntegrityViolationException.class, () -> pr.save(nullPerson));
    }
}