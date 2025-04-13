package com.yomeekoko.tredbase_payment_system.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance;

    @OneToOne(mappedBy = "account")
    private Parent parent;  // Link to Parent

    @OneToOne(mappedBy = "account")
    private Student student;  // Link to Student


}
