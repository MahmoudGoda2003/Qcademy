package com.example.backend.PersonTests;

import com.example.backend.person.model.Person;
import com.example.backend.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PersonRepositoryTest {

    @Autowired
    PersonRepository pr;

    @BeforeEach
    void setUp() {
        pr.deleteAll();
    }

    private List<Person> createTestData() {
        return Arrays.asList(
                new Person("ali", "amr", "aliamr@gmail.com", "12345679", "2020-11-12", "photo0.jpg"),
                new Person("ali", "ahmed", "aliahmed@gmail.com", "12345679", "2015-11-12", "photo1.jpg"),
                new Person("Mohammed", "ahmed", "Mohammedahmed@gmail.com", "12345679", "2006-1-12", "photo2.jpg"),
                new Person("Mohammed", "amr", "Mohammedamr@gmail.com", "12345679", "2018-12-1", "photo3.jpg"),
                new Person("amr", "ahmed", "amrahmed@gmail.com", "12345679", "2015-6-2", "photo4.jpg"),
                new Person("Mahmoud", "Mohammed", "MahmoudMohammed@gmail.com", "12345679", "2009-1-11", "photo5.jpg")
        );
    }

    @Test
    void testSave() {
        Person p = new Person("Mahmoud", "amr", "Mahmoudamr@gmail.com", "12345679", "2018-11-2", "photo7.jpg");
        pr.save(p);
        List<Person> personsTest = pr.findAll();
        assertEquals(1, personsTest.size());
        assertEquals(p, personsTest.get(0));
    }

    @Test
    void testSaveAll() {
        List<Person> persons = createTestData();
        pr.saveAll(persons);
        List<Person> personsTest = pr.findAll();
        assertEquals(persons.size(), personsTest.size());
        assertTrue(personsTest.containsAll(persons));
    }

    @Test
    void testFindAll() {
        List<Person> persons = createTestData();
        pr.saveAll(persons);
        List<Person> personsTest = pr.findAll();
        assertEquals(persons.size(), personsTest.size());
        assertTrue(personsTest.containsAll(persons));
    }

    @Test
    void testFindByEmail() {
        Person p = new Person("Mohammed", "ahmed", "Mohammedahmed@gmail.com", "12345679", "2006-1-12", "photo2.jpg");
        pr.save(p);
        Person personTest = pr.findByEmail("Mohammedahmed@gmail.com");
        assertNotNull(personTest);
        assertEquals("Mohammed", personTest.getFirstName());
    }

    @Test
    void testGetById() {
        Person p = new Person("Mahmoud", "ahmed", "Mohd@gmail.com", "12345679", "2006-1-12", "photo2.jpg");
        pr.save(p);
        Person personTest = pr.getOne(p.getId());
        assertEquals("Mahmoud", personTest.getFirstName());
    }

    @Test
    void testDeleteById() {
        List<Person> persons = createTestData();
        pr.saveAll(persons);
        long id = pr.findByEmail(persons.get(0).getEmail()).getId();
        pr.deleteById(id);
        assertFalse(pr.existsById(id));
        List<Person> personsTest = pr.findAll();
        assertEquals(5, personsTest.size());
    }

    @Test
    void testUpdateByEmail() {
        Person person = new Person("Mohammed", "ahmed", "Mohammedahmed@gmail.com", "12345679", "2006-1-12", "photo2.jpg");
        pr.save(person);
        Person p = pr.findByEmail("Mohammedahmed@gmail.com");
        Long id = p.getId();
        p.setEmail("MoSalah@gmail.com");
        p.setDateOfBirth("2005-9-14");
        pr.save(p);
        assertEquals("MoSalah@gmail.com", pr.getById(id).getEmail());
    }

    @Test
    void testSaveUniqueConstraintViolation() {
        List<Person> persons = createTestData();
        pr.saveAll(persons);
        Person duplicatePerson = new Person("Ali", "amr", "aliamr@gmail.com", "12345679", "2020-11-12", "photo0.jpg");
        assertThrows(DataIntegrityViolationException.class, () -> pr.save(duplicatePerson));
    }

    @Test
    void testSaveNullConstraintViolation() {
        Person nullPerson = new Person();
        nullPerson.setFirstName("Mohamed");
        nullPerson.setLastName("yossef");
        assertThrows(DataIntegrityViolationException.class, () -> pr.save(nullPerson));
    }

    @Test
    void testExistsPersonByEmail() {
        List<Person> persons = createTestData();
        pr.saveAll(persons);

        assertTrue(pr.existsPersonByEmail("aliamr@gmail.com"));
        assertTrue(pr.existsPersonByEmail("aliahmed@gmail.com"));
        assertTrue(pr.existsPersonByEmail("Mohammedahmed@gmail.com"));
        assertFalse(pr.existsPersonByEmail("nonexistent@gmail.com"));
    }
}
