package com.dersalik.payment.domain.payment;

public record Payment(Long id,
                      Double amount,
                      String method,
                      Long userID) {
}
