package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> getAllMoviesQuery =
                    session.createQuery("from CinemaHall", CinemaHall.class);
            return getAllMoviesQuery.getResultList();
        }
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.addAbstract(cinemaHall);
    }
}
