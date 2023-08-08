package com.dersalik.payment.infrastructure.configuration;


import com.dersalik.payment.domain.payment.GetPaymentsQuery;
import com.dersalik.payment.domain.payment.PaymentStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    GetPaymentsQuery getPaymentsQuery(PaymentStore paymentStore) {
        return new GetPaymentsQuery(paymentStore);
    }
}
