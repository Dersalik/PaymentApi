package com.dersalik.payment.infrastructure.endpoints;


import com.dersalik.payment.domain.payment.AddPaymentCommand;
import com.dersalik.payment.domain.payment.DeletePaymentCommand;
import com.dersalik.payment.domain.payment.GetPaymentsQuery;
import com.dersalik.payment.domain.payment.UpdatePaymentCommand;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PaymentController {

private final GetPaymentsQuery getPaymentsQuery;
private final AddPaymentCommand addPaymentCommand;
private final DeletePaymentCommand deletePaymentCommand;
private final UpdatePaymentCommand updatePaymentCommand;


@PutMapping("users/{userId}/payments/{paymentId}")
public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long userId, @PathVariable Long paymentId, @RequestBody UpdatePaymentRequest paymentRequest) {
    return updatePaymentCommand.execute(new UpdatePaymentCommand.Input(paymentId, paymentRequest.amount(), paymentRequest.method(), userId))
            .map(payment -> ResponseEntity.ok(new PaymentResponse(payment.getId(), payment.getUserID(), payment.getAmount(), payment.getMethod())))
            .getOrElseThrow(error -> new RuntimeException("Error"));

}

record UpdatePaymentRequest(double amount, String method) {
}
@DeleteMapping("users/{userId}/payments/{paymentId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletePayment(@PathVariable Long userId, @PathVariable Long paymentId) {
    deletePaymentCommand.execute(new DeletePaymentCommand.Input(paymentId));
}

@GetMapping("users/{userId}/payments")
public ResponseEntity<PaymentsResponse> getPayments(@PathVariable Long userId) {
    final var output = getPaymentsQuery.execute(new GetPaymentsQuery.Input(userId));
    return ResponseEntity.ok(new PaymentsResponse(
            output.getPayments().stream()
                    .map(payment -> new PaymentResponse(payment.getId(),payment.getUserID(),payment.getAmount(),payment.getMethod()))
                    .toList()
    ));
}


@PostMapping("users/{userId}/payments")
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<PaymentResponse> addPayment(@PathVariable Long userId, @RequestBody PaymentRequest paymentRequest) {
    return addPaymentCommand.execute(new AddPaymentCommand.Input( paymentRequest.amount(), paymentRequest.method(), userId))
            .map(payment -> ResponseEntity.ok(new PaymentResponse(payment.getId(),payment.getUserID(),payment.getAmount(),payment.getMethod())))
            .getOrElseThrow(error -> new RuntimeException("Error"));
}

record PaymentRequest(Double amount, String method) {

}

  record PaymentsResponse(List<PaymentResponse> payments) {
  }
  record PaymentResponse(Long id, Long userId, Double amount, String method) {
  }

}
