package com.example.backend.person.repository;

import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * find the person record by Email, and it returns null if the email is not found
     * parameters: Email
     *
     * @return Person
     */
    Person findByEmail(String email);

    /**
     * find the person record by Email, and it returns null if the email is not found
     * parameters: Email
     *
     * @return Person
     */
    boolean existsPersonByEmail(String email);

    @Query("SELECT person.role FROM Person person WHERE person.id = :userId")
    Role findRoleById(@Param("userId") Long userId);


    @Transactional
    @Modifying
    @Query("UPDATE Person u SET u.role = :newRole WHERE u.id = :userId")
    void updateRoleById(@Param("userId") Long userId, @Param("newRole") Role newRole);

}
