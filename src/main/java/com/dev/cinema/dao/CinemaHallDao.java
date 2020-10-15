package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao extends AbstractDao<CinemaHall> {
    List<CinemaHall> getAll();
}
