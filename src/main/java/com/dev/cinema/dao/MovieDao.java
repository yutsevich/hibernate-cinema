package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieDao {
    List<Movie> getAll();

    Movie add(Movie movie);
}
