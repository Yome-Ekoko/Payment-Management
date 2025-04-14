package com.yomeekoko.tredbase_payment_system.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Parent parent;  // Link to Parent

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;  // Link to Student
}