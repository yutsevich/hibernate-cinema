package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieRequestDto;
import com.dev.cinema.dto.MovieResponseDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(modelMapper.map(movieRequestDto, Movie.class));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movie -> modelMapper.map(movie, MovieResponseDto.class))
                .collect(Collectors.toList());
    }
}
