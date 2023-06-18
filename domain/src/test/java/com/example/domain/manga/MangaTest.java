package com.example.domain.manga;

import com.example.domain.shared.exception.DomainException;
import com.example.domain.shared.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MangaTest {
  @Test
  void givenAValidParams_whenCallNewManga_thenInstantiateAManga() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;

    final var actualManga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, expectedIsActive);

    Assertions.assertNotNull(actualManga);
    Assertions.assertNotNull(actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertNotNull(actualManga.getCreatedAt());
    Assertions.assertNotNull(actualManga.getUpdatedAt());
    Assertions.assertNull(actualManga.getDeletedAt());
  }

  @SuppressWarnings("java:S5778")
  @Test
  void givenAnInvalidNullName_whenCallNewMangaAndValidate_thenThrowException() {
    final String expectedName = null;
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;

    final var actualManga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, expectedIsActive);

    final var actualException = Assertions.assertThrows(
        DomainException.class,
        () -> actualManga.validate(new ThrowsValidationHandler())
    );

    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }

  @SuppressWarnings("java:S5778")
  @Test
  void givenAnInvalidEmptyName_whenCallNewMangaAndValidate_thenThrowException() {
    final String expectedName = " ";
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should not be empty";
    final var expectedErrorCount = 1;

    final var actualManga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, expectedIsActive);

    final var actualException = Assertions.assertThrows(
        DomainException.class,
        () -> actualManga.validate(new ThrowsValidationHandler())
    );

    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }

  @SuppressWarnings("java:S5778")
  @Test
  void givenAnInvalidNameLength_whenCallNewMangaAndValidate_thenThrowException() {
    final String expectedName = """
          lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam at facilisis nisi. Nulla bibendum lacus venenatis, accumsan sapien sagittis, ornare orci. Vestibulum hendrerit auctor egestas. Integer semper euismod ullamcorper. Nam congue malesuada odio, a porta quam sollicitudin eget. Aenean ut nisi blandit, placerat augue sed, hendrerit tortor. Vivamus elementum ornare hendrerit. Nam sit amet semper elit. Duis at felis elit. Proin sagittis nisl non nunc lacinia porta. Donec at velit neque. Ut elementum, ligula sit amet gravida ullamcorper, tellus neque ultrices sapien, ut efficitur sem velit vitae mi. Aliquam eget mi purus. Aenean scelerisque tristique tortor, sed suscipit nibh porttitor id. Phasellus non diam enim. Etiam viverra porttitor lectus quis placerat.
          
          Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Etiam aliquet, massa a efficitur lacinia, elit eros elementum metus, vel tempor lectus eros at velit. Suspendisse mattis aliquet justo sed ullamcorper. Mauris dignissim enim ut augue scelerisque, convallis fermentum nisi condimentum. Phasellus velit elit, dignissim vel augue id, tempus dictum urna. Vestibulum consequat sapien porta, molestie enim at, laoreet mi. In vel varius nulla. Donec sed rhoncus elit. Duis lobortis eget urna ac lobortis. Sed arcu tellus, vestibulum id odio quis, venenatis condimentum nibh. Morbi a ex sodales, euismod lectus sed, lobortis tellus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In fringilla, nisi ut mattis sagittis, tortor magna pretium turpis, ut tempus quam sem a enim. Fusce vel convallis nisl.
          
          Mauris velit elit, laoreet ac eros ut, egestas mollis turpis. In vel nibh nulla. Sed varius vulputate placerat. Fusce mattis varius sagittis. Mauris commodo libero vitae aliquam semper. Phasellus efficitur metus arcu, at iaculis dolor hendrerit vitae. Etiam malesuada scelerisque lacus, id tincidunt est facilisis id. Aenean lectus turpis, scelerisque at consectetur faucibus, interdum in eros. Integer laoreet, lorem eget.
        """;
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should not be longer than " + MangaValidator.MAX_LENGTH_NAME + " characters";
    final var expectedErrorCount = 1;

    final var actualManga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, expectedIsActive);

    final var actualException = Assertions.assertThrows(
        DomainException.class,
        () -> actualManga.validate(new ThrowsValidationHandler())
    );

    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }

  @Test
  void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
    final var expectedName = "any_name";
    final var expectedDescription = "";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;

    final var actualManga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, expectedIsActive);

    Assertions.assertDoesNotThrow(() -> actualManga.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualManga);
    Assertions.assertNotNull(actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertNotNull(actualManga.getCreatedAt());
    Assertions.assertNotNull(actualManga.getUpdatedAt());
    Assertions.assertNull(actualManga.getDeletedAt());
  }

  @Test
  void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = false;

    final var actualManga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, expectedIsActive);

    Assertions.assertDoesNotThrow(() -> actualManga.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualManga);
    Assertions.assertNotNull(actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertNotNull(actualManga.getCreatedAt());
    Assertions.assertNotNull(actualManga.getUpdatedAt());
    Assertions.assertNotNull(actualManga.getDeletedAt());
  }

  @Test
  void givenAValidCompletedManga_whenCallMarkAsNotCompleted_thenReturnMangaNotCompleted() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;

    final var manga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, true, expectedIsActive);

    Assertions.assertDoesNotThrow(() -> manga.validate(new ThrowsValidationHandler()));

    final var createdAt = manga.getCreatedAt();
    final var updatedAt = manga.getUpdatedAt();

    Assertions.assertTrue(manga.isActive());
    Assertions.assertNull(manga.getDeletedAt());

    final var actualManga = manga.markAsNotCompleted();

    Assertions.assertDoesNotThrow(() -> actualManga.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(manga.getId(), actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertEquals(createdAt, actualManga.getCreatedAt());
    Assertions.assertTrue(actualManga.getUpdatedAt().isAfter(updatedAt));
    Assertions.assertNull(actualManga.getDeletedAt());
  }

  @Test
  void givenAValidNotCompletedManga_whenCallMarkAsCompleted_thenReturnMangaCompleted() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = true;
    final var expectedIsActive = true;

    final var manga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, false, expectedIsActive);

    Assertions.assertDoesNotThrow(() -> manga.validate(new ThrowsValidationHandler()));

    final var createdAt = manga.getCreatedAt();
    final var updatedAt = manga.getUpdatedAt();

    Assertions.assertTrue(manga.isActive());
    Assertions.assertNull(manga.getDeletedAt());

    final var actualManga = manga.markAsCompleted();

    Assertions.assertDoesNotThrow(() -> actualManga.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(manga.getId(), actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertEquals(createdAt, actualManga.getCreatedAt());
    Assertions.assertTrue(actualManga.getUpdatedAt().isAfter(updatedAt));
    Assertions.assertNull(actualManga.getDeletedAt());
  }

  @Test
  void givenAValidActiveManga_whenCallDeactivate_thenReturnMangaInactivated() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_name";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = false;

    final var manga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, true);

    Assertions.assertDoesNotThrow(() -> manga.validate(new ThrowsValidationHandler()));

    final var createdAt = manga.getCreatedAt();
    final var updatedAt = manga.getUpdatedAt();

    Assertions.assertTrue(manga.isActive());
    Assertions.assertNull(manga.getDeletedAt());

    final var actualManga = manga.deactivate();

    Assertions.assertDoesNotThrow(() -> actualManga.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(manga.getId(), actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertEquals(createdAt, actualManga.getCreatedAt());
    Assertions.assertTrue(actualManga.getUpdatedAt().isAfter(updatedAt));
    Assertions.assertNotNull(actualManga.getDeletedAt());
  }

  @Test
  void givenAValidInactiveManga_whenCallActivate_thenReturnMangaActivated() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_description";
    final var expectedIsAvailable = true;
    final var expectedIsCompleted = false;
    final var expectedIsActive = true;

    final var manga = Manga.newManga(expectedName, expectedDescription, expectedIsAvailable, expectedIsCompleted, false);

    Assertions.assertDoesNotThrow(() -> manga.validate(new ThrowsValidationHandler()));

    final var createdAt = manga.getCreatedAt();
    final var updatedAt = manga.getUpdatedAt();

    Assertions.assertFalse(manga.isActive());
    Assertions.assertNotNull(manga.getDeletedAt());

    final var actualManga = manga.activate();

    Assertions.assertDoesNotThrow(() -> actualManga.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(manga.getId(), actualManga.getId());
    Assertions.assertEquals(expectedName, actualManga.getName());
    Assertions.assertEquals(expectedDescription, actualManga.getDescription());
    Assertions.assertEquals(expectedIsAvailable, actualManga.isAvailable());
    Assertions.assertEquals(expectedIsCompleted, actualManga.isCompleted());
    Assertions.assertEquals(expectedIsActive, actualManga.isActive());
    Assertions.assertEquals(createdAt, actualManga.getCreatedAt());
    Assertions.assertTrue(actualManga.getUpdatedAt().isAfter(updatedAt));
    Assertions.assertNull(actualManga.getDeletedAt());
  }
}
