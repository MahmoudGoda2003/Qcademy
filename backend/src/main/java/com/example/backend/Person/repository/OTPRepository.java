package com.example.backend.Person.repository;

import com.example.backend.Person.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, String> {

    @Query("SELECT otp.OTP FROM OTP otp WHERE otp.email = :e_mail")
    String findOTPByEmail(@Param("e_mail") String email);
}