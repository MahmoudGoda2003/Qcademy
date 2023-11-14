package com.example.backend.model;

import com.example.backend.DTO.SignUpDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Persons_Data")
public class NotValidatedPerson {
    @Id
    private String email;
    @Column(name = "OTB", nullable = false)
    private int OTP;

    public NotValidatedPerson(String email) {
        this.email = email;
        Random random = new Random();
        this.OTP = random.nextInt(100000, 999999);
    }
}
