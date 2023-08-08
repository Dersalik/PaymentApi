package com.dersalik.payment.infrastructure;

import com.dersalik.payment.domain.errors.StructuredError;
import com.dersalik.payment.infrastructure.store.entities.PaymentEntity;
import com.dersalik.payment.infrastructure.store.repositories.PaymentRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
public class DatabasePaymentStore implements com.dersalik.payment.domain.payment.PaymentStore{


    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentResult> findAll(findAllPaymentsByUserIdParam param) {
        return paymentRepository.findByuserId(param.getUserId())
                .stream()
                .map(PaymentEntity::toStoreResult)
                .toList();
    }

    @Override
    public Option<PaymentResult> findById(findPaymentByIdParam param) {
        return null;
    }

    @Override
    public Either<StructuredError, PaymentResult> save(savePaymentParam param) {
        return null;
    }

    @Override
    public Either<StructuredError, PaymentResult> update(updatePaymentParam param) {
        return null;
    }

    @Override
    public Either<StructuredError, Void> delete(deletePaymentParam param) {
        return null;
    }
}
