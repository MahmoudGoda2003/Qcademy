package com.example.backend.person.repository;

import com.example.backend.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * find the person record by Email, and it returns null if the email is not found
     * parameters: Email
     * @return Person
     */
    Person findByEmail(String email);
}
