package com.example.domain.manga;

public record MangaSearchQuery(
    int page,
    int perPage,
    String terms,
    String sort,
    String direction
) {
}
