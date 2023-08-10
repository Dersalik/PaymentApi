package com.dersalik.payment.domain.payment;


import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
public class DeletePaymentCommand {


    private final PaymentStore paymentStore;



    public void execute(Input input) {
        paymentStore.delete(input.toParams());
    }
    @Value
    public static class Input {
        private final Long id;

        private PaymentStore.deletePaymentParam toParams() {
            return new PaymentStore.deletePaymentParam(id);
        }

    }
}
