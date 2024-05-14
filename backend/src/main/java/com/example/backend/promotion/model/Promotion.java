package com.example.backend.promotion.model;

import com.example.backend.person.model.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promotion")
public class Promotion {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

}
