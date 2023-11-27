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
@Table(name = "Person_OTP")
public class OTP {
    @Id
    private String email;
    @Column(name = "OTP", nullable = false)
    private String OTP;
    @Column(name = "Time_Created")
    @UpdateTimestamp
    private Instant timeCreated;


    public OTP(String email, String OTP) {
        this.email = email;
        this.OTP = OTP;
    }
}
