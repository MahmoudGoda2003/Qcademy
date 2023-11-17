package com.example.backend.Person.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "PersonOTP")
public class OTPs {
    @Id
    private String email;
    @Column(name = "OTP", nullable = false)
    private String OTP;
    @Column(name = "TimeCreated", nullable = false)
    @UpdateTimestamp
    private Instant timeCreated;


    public OTPs(String email, String OTP) {
        this.email = email;
        this.OTP = OTP;
    }
}
