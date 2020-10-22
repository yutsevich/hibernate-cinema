package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> criteriaQuery = criteriaBuilder
                    .createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = criteriaQuery.from(ShoppingCart.class);
            root.fetch("user", JoinType.INNER);
            root.fetch("tickets", JoinType.LEFT);
            Predicate checkUser = criteriaBuilder.equal(root.get("user"), user);
            return session.createQuery(criteriaQuery.where(checkUser)).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get user with email " + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not update shopping cart entity "
                    + shoppingCart.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        super.addAbstract(shoppingCart);
    }
}
