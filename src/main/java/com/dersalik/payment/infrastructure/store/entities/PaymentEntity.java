package com.dersalik.payment.infrastructure.store.entities;

import com.dersalik.payment.domain.payment.PaymentStore;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String method;

    private Long userId; // Foreign key reference to the User


    public PaymentStore.PaymentResult toStoreResult() {
        return new PaymentStore.PaymentResult(
                id,
                amount,
                method,
                userId
        );
    }

}
