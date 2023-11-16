package com.example.backend.Person.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "TimeCreated", nullable = false, updatable = false)
    @CreationTimestamp
    private long timeCreated;


    public OTPs(String email, String OTP) {
        this.email = email;
        this.OTP = OTP;
        this.timeCreated = System.currentTimeMillis();
    }
}
