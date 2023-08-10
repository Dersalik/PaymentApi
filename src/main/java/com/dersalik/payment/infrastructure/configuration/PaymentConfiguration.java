package com.dersalik.payment.infrastructure.configuration;


import com.dersalik.payment.domain.payment.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    GetPaymentsQuery getPaymentsQuery(PaymentStore paymentStore) {
        return new GetPaymentsQuery(paymentStore);
    }

    @Bean
    DeletePaymentCommand deletePaymentCommand(PaymentStore paymentStore) {
        return new DeletePaymentCommand(paymentStore);
    }

    @Bean
    UpdatePaymentCommand updatePaymentCommand(PaymentStore paymentStore) {
        return new UpdatePaymentCommand(paymentStore);
    }

    @Bean
    AddPaymentCommand addPaymentCommand(PaymentStore paymentStore) {
        return new AddPaymentCommand(paymentStore);
    }
}
