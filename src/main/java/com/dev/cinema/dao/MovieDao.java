package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    List<Movie> getAll();

    Movie add(Movie movie);

    Optional<Movie> get(Long id);
}
