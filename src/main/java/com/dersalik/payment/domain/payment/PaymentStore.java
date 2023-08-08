package com.dersalik.payment.domain.payment;

import com.dersalik.payment.domain.errors.StructuredError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.Value;

import java.util.List;
import java.util.Optional;

public interface PaymentStore {
    List<PaymentResult> findAll(findAllPaymentsByUserIdParam param);
    Option<PaymentResult> findById(findPaymentByIdParam param);
    Either<StructuredError, PaymentResult> save(savePaymentParam param);

    Either<StructuredError, PaymentResult> update(updatePaymentParam param);

    Either<StructuredError, Void> delete(deletePaymentParam param);


    @Value
    class findAllPaymentsByUserIdParam {
        private final Long userId;

    }


    @Value
    class savePaymentParam {
        private final Double amount;
        private final String method;
        private final Long userID;
    }

    @Value
    class updatePaymentParam {
        private final Long id;
        private final Double amount;
        private final String method;
        private final Long userID;
    }

    @Value
    class deletePaymentParam {
        private final Long id;
    }

    @Value
    class findPaymentByIdParam {
        private final Long id;
    }
    @Value
     class PaymentResult {
        private final Long id;
        private final Double amount;
        private final String method;
        private final Long userID;

        Payment toDomain() {
            return new Payment(id, amount, method, userID);
        }
    }
}
