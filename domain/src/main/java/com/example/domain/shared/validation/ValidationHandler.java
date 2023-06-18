package com.example.domain.shared.validation;

import java.util.List;

public interface ValidationHandler {
  ValidationHandler append(CustomError error);

  ValidationHandler append(ValidationHandler handler);

  ValidationHandler validate(Validation validation);

  List<CustomError> getErrors();

  default boolean hasError() {
    return getErrors() != null && getErrors().isEmpty();
  }

  interface Validation {
    void validate();
  }
}
