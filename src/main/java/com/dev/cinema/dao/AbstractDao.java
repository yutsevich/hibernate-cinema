package com.dev.cinema.dao;

import com.dev.cinema.dao.impl.CinemaHallDaoImpl;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface AbstractDao<T> {
    Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);

    default T add(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            logger.info(entity.getClass().getSimpleName() + " "
                    + entity.toString() + "is added");
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert "
                    + entity.getClass().getSimpleName()
                    + entity.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
