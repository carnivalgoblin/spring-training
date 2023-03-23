package org.rcprdn.springtraining.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
@Log
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  public final MessageSource messageSource;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(RuntimeException rex, Exception ex) {

    String message = messageSource.getMessage("internalServerError", null, LocaleContextHolder.getLocale());
    return new ResponseEntity<>(ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> entityNotFoundException(RuntimeException rex, WebRequest request) {

    String msg = getMessageSource().getMessage("entityNotFoundError", null, LocaleContextHolder.getLocale());
    return handleExceptionInternal(rex, msg, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

}
