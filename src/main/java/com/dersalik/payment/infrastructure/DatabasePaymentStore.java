package com.dersalik.payment.infrastructure;

import com.dersalik.payment.domain.errors.StructuredError;
import com.dersalik.payment.infrastructure.store.entities.PaymentEntity;
import com.dersalik.payment.infrastructure.store.repositories.PaymentRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
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
        return Try.of(() -> PaymentEntity.of(param))
                .map(paymentRepository::save)
                .toEither(new StructuredError("Error while storing payment", com.dersalik.payment.domain.errors.ErrorType.SERVER_ERROR))
                .map(PaymentEntity::toStoreResult);

    }

    @Override
    public Either<StructuredError, PaymentResult> update(updatePaymentParam param) {
         return Option.ofOptional(paymentRepository.findById(param.getId()))
                 .toEither(new StructuredError("Couldn't find payment", com.dersalik.payment.domain.errors.ErrorType.NOT_FOUND_ERROR))
                 .map(paymentEntity -> {
                     paymentEntity.setAmount(param.getAmount());
                     paymentEntity.setUserId(param.getUserID());
                     paymentEntity.setMethod(param.getMethod());
                     paymentEntity.setId(param.getId());
                     return paymentEntity;
                 })
                 .map(paymentRepository::save)
                 .map(PaymentEntity::toStoreResult);
    }

    @Override
    public Either<StructuredError, Void> delete(deletePaymentParam param) {

        return Try.run(() -> paymentRepository.deleteById(param.getId()))
                .toEither(new StructuredError("Error while deleting payment", com.dersalik.payment.domain.errors.ErrorType.SERVER_ERROR));
    }

}
