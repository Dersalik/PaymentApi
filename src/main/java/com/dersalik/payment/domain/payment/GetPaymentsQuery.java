package com.dersalik.payment.domain.payment;

import com.dersalik.payment.domain.errors.StructuredError;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class GetPaymentsQuery {
  private final PaymentStore paymentStore;


    public Output execute(Input input) {
        final var payments= paymentStore.findAll(input.toParams())
                .stream()
                .map(PaymentStore.PaymentResult::toDomain)
                .map(ItemOutput::of)
                .toList();


        return new Output(payments);


    }

    @Value
    public static class Input {
        private final Long userId;


        private PaymentStore.findAllPaymentsByUserIdParam toParams() {
            return new PaymentStore.findAllPaymentsByUserIdParam(userId);
        }

    }




    @Value
    public static class Output {
         final List<ItemOutput> payments;
    }

    @Value
    public static class ItemOutput {
         final Long id;
         final Double amount;
         final String method;
         final Long userID;


        static ItemOutput of(Payment payment) {
            return new ItemOutput(payment.id(), payment.amount(), payment.method(), payment.userID());
        }
    }



}
