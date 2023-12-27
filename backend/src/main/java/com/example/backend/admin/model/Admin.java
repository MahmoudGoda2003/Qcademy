package com.example.backend.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
