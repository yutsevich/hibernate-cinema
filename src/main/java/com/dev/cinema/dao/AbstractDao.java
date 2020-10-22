package com.dev.cinema.dao;

import com.dev.cinema.dao.impl.CinemaHallDaoImpl;
import com.dev.cinema.exceptions.DataProcessingException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao<T> {
    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);
    protected final SessionFactory sessionFactory;

    @Autowired
    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T addAbstract(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            logger.info(entity.getClass().getSimpleName() + " "
                    + entity + "is added");
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert "
                    + entity.getClass().getSimpleName()
                    + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
