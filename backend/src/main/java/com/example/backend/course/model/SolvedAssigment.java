package com.example.backend.course.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SolvedAssigment_data")
public class SolvedAssigment {
    @Id
    @Column(name = "solutoin_id")
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Number;

    @Column(name = "solution_url", nullable = false)
    private String solutionURL;

    @ManyToOne
    @JoinColumn(name = "assigment")
    private Assigment assigment;

}
