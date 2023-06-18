package com.example.domain.shared.validation.handler;

import com.example.domain.shared.exception.DomainException;
import com.example.domain.shared.validation.CustomError;
import com.example.domain.shared.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
  @Override
  public ValidationHandler append(CustomError error) {
    throw DomainException.with(error);
  }

  @Override
  public ValidationHandler append(ValidationHandler handler) {
    throw DomainException.with(handler.getErrors());
  }

  @Override
  public ValidationHandler validate(Validation validation) {
    try {
      validation.validate();
    } catch (final Exception ex) {
      throw DomainException.with(List.of(new CustomError(ex.getMessage())));
    }

    return this;
  }

  @Override
  public List<CustomError> getErrors() {
    return List.of();
  }

  @Override
  public boolean hasError() {
    return ValidationHandler.super.hasError();
  }
}
