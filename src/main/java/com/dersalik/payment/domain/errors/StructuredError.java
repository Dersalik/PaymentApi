package com.dersalik.payment.domain.errors;

public record StructuredError(String message, ErrorType type) {
}
