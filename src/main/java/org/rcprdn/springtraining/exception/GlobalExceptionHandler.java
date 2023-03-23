package org.rcprdn.springtraining.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Log
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(RuntimeException rex, Exception ex) {
    logger.warn("INTERNAL SERVER ERROR");
    return new ResponseEntity<>(ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> entityNotFoundException(RuntimeException rex, WebRequest request) {
    logger.warn("WARNING! ENTITY NOT FOUND");
    return handleExceptionInternal(rex, rex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

}
