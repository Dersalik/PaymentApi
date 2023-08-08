package com.dersalik.payment.infrastructure.store.repositories;

import com.dersalik.payment.infrastructure.store.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
