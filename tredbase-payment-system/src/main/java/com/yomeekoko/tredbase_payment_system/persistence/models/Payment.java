package com.yomeekoko.tredbase_payment_system.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class Payment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "parent_id")
        private Parent parent; // Parent making the payment

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student; // Student for whom the payment is made

        private Double amount; // Payment amount

        private LocalDateTime date; // Payment date

        private Double dynamicRate; // Fee applied to the payment (e.g., dynamic fee)
    }


