package com.example.backend.Person.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "PersonOTP")
public class NotValidatedPerson {
    @Id
    private String email;
    @Column(name = "OTP", nullable = false)
    private String OTP;
    @Column(name = "TimeCreated", nullable = false, updatable = false)
    @CreationTimestamp
    private long timeCreated;


    public NotValidatedPerson(String email, String OTP) {
        this.email = email;
        this.OTP = OTP;
        this.timeCreated = System.currentTimeMillis();
    }
}
