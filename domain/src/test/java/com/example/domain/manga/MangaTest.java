package com.example.domain.manga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MangaTest {
  @Test
  void givenAValidParams_whenCallNewManga_thenInstantiateAManga() {
    final var expectedName = "any_name";
    final var expectedDescription = "any_name";
    final var expectedIsCompleted = false;
    final var expectedIsAvailable = true;
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
}
