package com.example.domain;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {
  protected AggregateRoot(ID id) {
    super(id);
  }
}
