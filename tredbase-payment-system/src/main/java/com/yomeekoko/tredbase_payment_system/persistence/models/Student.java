package com.yomeekoko.tredbase_payment_system.persistence.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yomeekoko.tredbase_payment_system.utils.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String email;
        private RoleEnum role;


    @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "account_id")
        private Account account; // Student's account

        @ManyToMany(mappedBy = "students")
        @JsonBackReference
        private List<Parent> parents; // Many-to-many relationship if a child is shared by two parents
    }


