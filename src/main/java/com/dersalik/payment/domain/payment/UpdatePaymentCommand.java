package com.dersalik.payment.domain.payment;

import com.dersalik.payment.domain.errors.StructuredError;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class UpdatePaymentCommand {

    private final PaymentStore paymentStore;

    public Either<StructuredError, Output> execute(Input input) {
        return paymentStore.update(input.toParams())
                .map(PaymentStore.PaymentResult::toDomain)
                .map(Output::of);
    }


    @Value
    public static class Input {
        private final Long id;
        private final Double amount;
        private final String method;
        private final Long userID;

        private PaymentStore.updatePaymentParam toParams() {
            return new PaymentStore.updatePaymentParam(id, amount, method, userID);
        }

    }


    @Value
    public static class Output {
        private final Long id;
        private final Double amount;
        private final String method;
        private final Long userID;

        static Output of(Payment payment) {
            return new Output(payment.id(), payment.amount(), payment.method(), payment.userID());
        }
    }


}
