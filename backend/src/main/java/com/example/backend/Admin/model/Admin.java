package com.example.backend.Admin.model;


import com.example.backend.Person.DTO.SignUpDTO;
import com.example.backend.Person.model.Person;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Admin")
public class Admin {
    @Id
    private String person_id;

    public Admin(String person_id) {
        this.person_id = person_id;
    }
}
