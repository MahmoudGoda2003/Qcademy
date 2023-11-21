package com.example.backend.Person.repository;

import com.example.backend.Person.model.Person;
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

    /**
     * find the person record by name, and it returns null if the email is not found
     * parameters: firstName and lastName
     * @return list of persons
     */
    List<Person> findAllByFirstNameAndLastName(String firstName, String LastName);

    /**
     * find if this email exist or not
     * parameters: Email
     * @return Boolean [true, False]
     */
    Boolean existsByEmail(String email);

    /**
     * عشان هشام القمرررررر ميزعلش
     * find the password by email, and it returns null if the email is not found
     * parameters: email
     * @return String password
     */
    @Query("SELECT person.EncryptedPassword FROM Person person WHERE person.email = :e")
    String findPasswordByEmail(@Param("e") String email);
}
