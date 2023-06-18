package com.example.domain.manga;

import java.time.Instant;
import java.util.UUID;

public class Manga {
  public String id;
  private String name;
  private String description;
  private boolean isActive;
  private boolean isCompleted;
  private boolean isAvailable;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;

  @SuppressWarnings("java:S107")
  private Manga(
      final String id,
      final String name,
      final String description,
      final boolean isAvailable,
      final boolean isCompleted,
      final boolean isActive,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.isAvailable = isAvailable;
    this.isCompleted = isCompleted;
    this.isActive = isActive;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }

  public static Manga newManga(final String name, final String description, final boolean isAvailable, final boolean isCompleted, final boolean isActive) {
    final var id = UUID.randomUUID().toString();
    return new Manga(id, name, description, isAvailable, isCompleted, isActive, Instant.now(), Instant.now(), null);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isActive() {
    return isActive;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }
}
