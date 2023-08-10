package com.dersalik.payment.infrastructure.store.entities;

import com.dersalik.payment.domain.payment.PaymentStore;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    @Size(max = 1)
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

    public static PaymentEntity of(PaymentStore.savePaymentParam params) {
        return new PaymentEntity(
                null,
                params.getAmount(),
                params.getMethod(),
                params.getUserID()

        );
    }

}
