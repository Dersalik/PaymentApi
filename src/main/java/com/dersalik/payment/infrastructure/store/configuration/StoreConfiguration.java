package com.dersalik.payment.infrastructure.store.configuration;

import com.dersalik.payment.domain.payment.PaymentStore;
import com.dersalik.payment.infrastructure.DatabasePaymentStore;
import com.dersalik.payment.infrastructure.store.repositories.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfiguration {

    @Bean
    PaymentStore paymentStore(PaymentRepository userRepository) {
        return new DatabasePaymentStore(userRepository);
    }

}