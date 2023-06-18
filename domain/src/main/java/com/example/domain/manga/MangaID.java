package com.example.domain.manga;

import com.example.domain.shared.Identifier;

import java.util.Objects;
import java.util.UUID;

public class MangaID extends Identifier {
  private final String value;

  private MangaID(final String value) {
    Objects.requireNonNull(value);
    this.value = value;
  }

  public static MangaID unique() {
    return MangaID.from(UUID.randomUUID());
  }

  public static MangaID from(final String id) {
    return new MangaID(id);
  }

  public static MangaID from(final UUID id) {
    return new MangaID(id.toString().toLowerCase());
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MangaID that = (MangaID) o;
    return Objects.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
