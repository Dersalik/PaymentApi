package com.dersalik.payment.infrastructure.endpoints;


import com.dersalik.payment.domain.payment.GetPaymentsQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PaymentController {

private final GetPaymentsQuery getPaymentsQuery;



@GetMapping("users/{userId}/payments")
public ResponseEntity<PaymentsResponse> getPayments(@PathVariable Long userId) {
    final var output = getPaymentsQuery.execute(new GetPaymentsQuery.Input(userId));
    return ResponseEntity.ok(new PaymentsResponse(
            output.getPayments().stream()
                    .map(payment -> new PaymentResponse(payment.getId(),payment.getUserID(),payment.getAmount(),payment.getMethod()))
                    .toList()
    ));
}


  record PaymentsResponse(List<PaymentResponse> payments) {
  }
  record PaymentResponse(Long id, Long userId, Double amount, String method) {
  }

}
