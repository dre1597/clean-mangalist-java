package com.example.domain.manga;

import com.example.domain.shared.validation.CustomError;
import com.example.domain.shared.validation.ValidationHandler;
import com.example.domain.shared.validation.Validator;

public class MangaValidator extends Validator {
  public static final int MAX_LENGTH_NAME = 255;
  private final Manga manga;

  public MangaValidator(final Manga manga, final ValidationHandler handler) {
    super(handler);
    this.manga = manga;
  }

  @Override
  public void validate() {
    checkNameConstraints();
  }

  private void checkNameConstraints() {
    final var name = this.manga.getName();

    if (name == null) {
      this.validationHandler().append(new CustomError("'name' should not be null"));
      return;
    }

    if (name.isBlank()) {
      this.validationHandler().append(new CustomError("'name' should not be empty"));
      return;
    }

    final int length = name.trim().length();

    if (length > MAX_LENGTH_NAME) {
      this.validationHandler().append(new CustomError("'name' should not be longer than " + MAX_LENGTH_NAME + " characters"));
    }
  }
}
