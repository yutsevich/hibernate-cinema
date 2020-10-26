package com.dev.cinema.dto;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieSessionRequestDto {
    private Movie movie;
    private CinemaHall cinemaHall;
    private LocalDateTime showTime;
}
