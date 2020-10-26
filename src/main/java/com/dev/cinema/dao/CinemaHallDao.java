package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    List<CinemaHall> getAll();

    CinemaHall add(CinemaHall cinemaHall);
}
