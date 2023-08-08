package com.dersalik.payment.infrastructure.store.repositories;

import com.dersalik.payment.infrastructure.store.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByuserId(Long userId);
}
