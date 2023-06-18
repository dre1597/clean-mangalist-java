package com.example.domain.shared;

@SuppressWarnings("java:S119")
public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {
  protected AggregateRoot(ID id) {
    super(id);
  }
}
