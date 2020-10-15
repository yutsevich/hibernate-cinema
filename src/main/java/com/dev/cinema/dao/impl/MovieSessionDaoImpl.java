package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> root = criteriaQuery.from(MovieSession.class);
            Predicate checkMovie = criteriaBuilder.equal(root.get("movie"), movieId);
            Predicate checkDate = criteriaBuilder.between(root.get("showTime"),
                    date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX));
            return session.createQuery(criteriaQuery.where(checkMovie, checkDate)).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get all available movie sessions "
                    + "by movie_id = " + movieId + " and date = " + date.toString(), e);
        }
    }
}
