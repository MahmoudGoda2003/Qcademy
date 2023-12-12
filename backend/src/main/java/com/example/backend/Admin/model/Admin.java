package com.example.backend.Admin.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Admin")
public class Admin {
    @Id
    private Long userId;


    public Admin(Long userId) {
        this.userId = userId;
    }
}
