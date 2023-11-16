package com.example.backend.Person.repository;

import com.example.backend.Person.model.OTPs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPs, String> {

    OTPs findByEmail(String email);

    Boolean deleteByTimeCreatedLessThan(long time);
}