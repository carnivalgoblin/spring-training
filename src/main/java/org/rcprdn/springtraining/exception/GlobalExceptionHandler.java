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

import java.util.Locale;
import java.util.ResourceBundle;

@RestControllerAdvice
@Log
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  Locale locale = new Locale("fr");
  ResourceBundle errorBundle = ResourceBundle.getBundle("ErrorResource", locale);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(RuntimeException rex, Exception ex) {
    logger.warn(errorBundle.getString("internalServerError"));
    return new ResponseEntity<>(ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> entityNotFoundException(RuntimeException rex, WebRequest request) {
    logger.warn(errorBundle.getString("entityNotFoundError"));
    return handleExceptionInternal(rex, rex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

}
