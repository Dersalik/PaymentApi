package com.dersalik.payment.infrastructure.store.entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment; // Foreign key reference to the Payment
}
