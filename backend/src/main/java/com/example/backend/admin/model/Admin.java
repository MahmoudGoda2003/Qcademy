package com.example.backend.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_id")
    private Long userId;


    public Admin(Long userId) {
        this.userId = userId;
    }
}
