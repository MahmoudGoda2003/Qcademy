package com.example.backend.Person.repository;

import com.example.backend.Person.model.NotValidatedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotValidatedPersonRepository extends JpaRepository<NotValidatedPerson, String> {

    NotValidatedPerson findByEmail(String email);

    Boolean deleteByTimeCreatedLessThan(long time);
}