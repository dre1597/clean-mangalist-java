package com.example.domain.shared.exception;

import com.example.domain.shared.validation.CustomError;

import java.util.List;

public class DomainException extends NoStackTraceException {
  private final List<CustomError> errors;

  public DomainException(final String message, final List<CustomError> errors) {
    super(message);
    this.errors = errors;
  }

  public static DomainException with(final CustomError error) {
    return new DomainException(error.message(), List.of(error));
  }

  public static DomainException with(final List<CustomError> errors) {
    return new DomainException("", errors);
  }

  public List<CustomError> getErrors() {
    return this.errors;
  }
}
