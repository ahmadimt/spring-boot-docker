package com.talcrafts.exception;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException() {
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
