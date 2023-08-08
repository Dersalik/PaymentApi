package com.dersalik.payment.infrastructure.error;

import com.dersalik.payment.infrastructure.error.endpoints.ErrorResponse;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidLanguageTagException.class)
    ResponseEntity<ErrorResponse> handleInvalidLanguageTagException(InvalidLanguageTagException ex) {
        logger.error("Handling InvalidLanguageTagException", ex);
        final var httpStatus = HttpStatus.BAD_REQUEST;
        final var errorResponse = new ErrorResponse("INVALID_REQUEST");
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(HibernateException.class)
    ResponseEntity<ErrorResponse> handleHibernateException(HibernateException ex) {
        logger.error("handling HibernateException", ex);
        final var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final var errorResponse = new ErrorResponse("Something went wrong");
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(ErrorStructureException.class)
    ResponseEntity<ErrorResponse> handleErrorStructureException(ErrorStructureException ex) {
        final var httpStatus = ex.getHttpStatus();
        final var errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, null, httpStatus);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        logger.error("Handle MethodArgumentTypeMismatchException", ex);
        final var errorResponse = new ErrorResponse("INVALID_REQUEST");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    ResponseEntity<ErrorResponse> handleFileTooBigException(MaxUploadSizeExceededException ex) {
        logger.error("Handling MaxUploadSizeExceededException", ex);
        final var errorResponse = new ErrorResponse("INVALID_REQUEST");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception ex) {
        logger.error("handling Exception", ex);
        final var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final var errorResponse = new ErrorResponse("Something went wrong");
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        logger.error("Handle BindException", ex);
        return handleBindingResultErrors(ex.getBindingResult());
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logger.error("Handle HttpMessageNotReadableException", ex);
        final var errorResponse = new ErrorResponse("INVALID_REQUEST");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logger.error("Handle MethodArgumentNotValidException", ex);
        final var errorResponse = new ErrorResponse("INVALID_REQUEST");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ResponseEntity<Object> handleBindingResultErrors(BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        Option.ofOptional(
                                        bindingResult.getFieldErrors().stream()
                                                .map(FieldError::getField)
                                                .findFirst()
                                )
                                .getOrElse("Something went wrong")
                )
        );
    }
}
