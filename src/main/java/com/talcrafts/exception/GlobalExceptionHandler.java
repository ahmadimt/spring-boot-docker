package com.talcrafts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

  static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity handleUserNotFoundException(UserNotFoundException unfe) {
    LOGGER.error("{}", unfe.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity handleIllegalArgumentException(IllegalArgumentException iae) {
    LOGGER.error("{}", iae.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
