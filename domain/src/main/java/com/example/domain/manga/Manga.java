package com.example.domain.manga;

import com.example.domain.shared.AggregateRoot;
import com.example.domain.shared.validation.ValidationHandler;

import java.time.Instant;

public class Manga extends AggregateRoot<MangaID> {
  private String name;
  private String description;
  private boolean active;
  private boolean completed;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;

  @SuppressWarnings("java:S107")
  private Manga(
      final MangaID id,
      final String name,
      final String description,
      final boolean completed,
      final boolean active,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt
  ) {
    super(id);
    this.name = name;
    this.description = description;
    this.completed = completed;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }

  public static Manga newManga(final String name, final String description, final boolean isCompleted, final boolean isActive) {
    final var id = MangaID.unique();
    final var now = Instant.now();
    final var deletedAt = isActive ? null : now;
    return new Manga(id, name, description, isCompleted, isActive, now, now, deletedAt);
  }

  @Override
  public void validate(ValidationHandler handler) {
    new MangaValidator(this, handler).validate();
  }

  public Manga markAsNotCompleted() {
    this.completed = false;
    this.updatedAt = Instant.now();
    return this;
  }

  public Manga markAsCompleted() {
    this.completed = true;
    this.updatedAt = Instant.now();
    return this;
  }

  public Manga deactivate() {
    if (getDeletedAt() == null) {
      this.deletedAt = Instant.now();
    }

    this.active = false;
    this.updatedAt = Instant.now();
    return this;
  }

  public Manga activate() {
    this.deletedAt = null;
    this.active = true;
    this.updatedAt = Instant.now();
    return this;
  }

  public Manga update(final String name, final String description, final boolean completed, final boolean active) {
    this.name = name;
    this.description = description;

    if (completed) {
      markAsCompleted();
    } else {
      markAsNotCompleted();
    }

    if (active) {
      activate();
    } else {
      deactivate();
    }

    this.updatedAt = Instant.now();
    return this;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isCompleted() {
    return completed;
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
