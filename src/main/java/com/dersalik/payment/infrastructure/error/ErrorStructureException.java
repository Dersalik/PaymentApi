package com.dersalik.payment.infrastructure.error;

import com.dersalik.payment.domain.errors.StructuredError;
import lombok.Getter;

import static com.dersalik.payment.infrastructure.error.ErrorTypeToHttpStatusMapper.httpStatus;


public class ErrorStructureException extends RuntimeException {

    @Getter
    private final int httpStatus;
    @Getter
    private final String message;

    public ErrorStructureException(StructuredError structuredError) {
        this.httpStatus = httpStatus(structuredError.type());
        this.message = structuredError.message();
    }
}
