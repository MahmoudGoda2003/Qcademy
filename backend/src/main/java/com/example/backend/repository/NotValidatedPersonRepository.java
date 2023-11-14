package com.example.backend.repository;

import com.example.backend.model.NotValidatedPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotValidatedPersonRepository extends JpaRepository<NotValidatedPerson, String> {

    NotValidatedPerson findByEmail(String email);
}