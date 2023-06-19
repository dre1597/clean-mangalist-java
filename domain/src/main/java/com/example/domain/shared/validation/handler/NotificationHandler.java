package com.example.domain.shared.validation.handler;

import com.example.domain.shared.exception.DomainException;
import com.example.domain.shared.validation.CustomError;
import com.example.domain.shared.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class NotificationHandler implements ValidationHandler {
  private final List<CustomError> errors;

  private NotificationHandler(final List<CustomError> errors) {
    this.errors = errors;
  }

  public static NotificationHandler create() {
    return new NotificationHandler(new ArrayList<>());
  }

  public static NotificationHandler create(final CustomError error) {
    return new NotificationHandler(new ArrayList<>()).append(error);
  }

  public static NotificationHandler create(final Throwable t) {
    return create(new CustomError(t.getMessage()));
  }

  @Override
  public NotificationHandler append(final CustomError error) {
    this.errors.add(error);
    return this;
  }

  @Override
  public NotificationHandler append(final ValidationHandler handler) {
    this.errors.addAll(handler.getErrors());
    return this;
  }

  @Override
  public NotificationHandler validate(final Validation validation) {
    try {
      validation.validate();
    } catch (final DomainException e) {
      this.errors.addAll(e.getErrors());
    } catch (final Exception t) {
      this.errors.add(new CustomError(t.getMessage()));
    }
    return this;
  }

  @Override
  public List<CustomError> getErrors() {
    return this.errors;
  }
}
