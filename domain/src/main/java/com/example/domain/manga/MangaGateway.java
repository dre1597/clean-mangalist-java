package com.example.domain.manga;

import com.example.domain.shared.pagination.Pagination;

import java.util.Optional;

public interface MangaGateway {
  Manga create(Manga manga);

  void deleteById(MangaID id);

  Optional<Manga> findById(MangaID id);

  Manga update(Manga manga);

  Pagination<Manga> findAll(MangaSearchQuery query);
}
