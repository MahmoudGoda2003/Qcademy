package com.example.backend.PersonTests;
import com.example.backend.Person.model.Person;
import com.example.backend.Person.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

        List<Person> persons = new ArrayList<>();
        persons.addAll(Arrays.asList(person).subList(0, 6));
        return persons;
    }
    @Test
    @Order(1)
    void test_save(){
        Person p = new Person("Mahmoud","amr","Mahmoudamr@gmail.com","12345679","2018-11-2","photo7.jpg");
        pr.save(p);
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(1,personsTest.size());
    }
    @Test
    @Order(2)
    void test_save_all(){
        List<Person> persons = some_data();
        // Save data in the table
        pr.saveAll(persons);
        // Test the number of Elements inserted (saved) in the table
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(7,personsTest.size());
    }
    @Test
    @Order(3)
    void test_find_all(){
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(7,personsTest.size());
        for (Person person : personsTest) {
            System.out.println(person.toString());
        }
    }
    @Test
    @Order(4)
    void test_find_by_email(){
        Person personTest = pr.findByEmail("Mohammedahmed@gmail.com");
        assertEquals("Mohammed",personTest.getFirstName());
    }
    @Test
    @Order(5)
    void test_find_by_id(){
        Person personTest = pr.findById(7L).get();
        assertEquals("Mahmoud",personTest.getFirstName());
    }

    @Test
    @Order(6)
    void test_delete_by_id(){
        pr.deleteById(3L);
        List<Person> personsTest = pr.findAll();
        Assertions.assertEquals(6,personsTest.size());
        for (Person person : personsTest) {
            System.out.println(person.toString());
        }
    }
    @Test
    @Order(7)
    void test_update_by_email(){
        Person p = pr.findByEmail("Mohammedahmed@gmail.com");
        Long id = p.getId();
        p.setEmail("MoSalah@gmail.com");
        p.setDateOfBirth("2005-9-14");
        pr.save(p);
        Assertions.assertEquals("MoSalah@gmail.com", pr.findById(id).get().getEmail());
        Assertions.assertEquals(6,pr.count());
    }
    @Test
    @Order(8)
    void test_find_by_name(){
        List<Person> personsTest = pr.findAllByFirstNameAndLastName("Mohammed","ahmed");
        Assertions.assertEquals(1,personsTest.size());
        for (Person person : personsTest) {
            System.out.println(person.toString());
        }
    }
    @Test
    @Order(9)
    void test_save_UniqueConstraintViolation() {
        Person p1 = new Person("Ahmed","ali","AhmedAli@gmail.com","12345679","2008-1-1","photo8.jpg");
        Person p2 = new Person("Ahmed","Mohamed","AhmedAli@gmail.com","12345679","2007-1-1","photo9.jpg");
        pr.save(p1);
        Assertions.assertEquals(7,pr.count());


        assertThrows(DataIntegrityViolationException.class, () -> pr.save(p2));
    }
    @Test
    @Order(10)
    void test_save_NullConstraintViolation() {

        Person nullPerson = new Person();
        nullPerson.setFirstName("Mohamed");
        nullPerson.setFirstName("yousef");
        assertThrows(DataIntegrityViolationException.class, () -> pr.save(nullPerson));
    }
    @Test
    @Order(11)
    void test_exists_by_email() {
        Assertions.assertEquals(true, pr.existsByEmail("MoSalah@gmail.com"));
    }
    @Test
    @Order(12)
    void test_exists_by_email_2() {
        Assertions.assertEquals(false, pr.existsByEmail("M.Salah@gmail.com"));
    }

}