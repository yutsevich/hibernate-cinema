package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder
                    .createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            root.fetch("tickets", JoinType.INNER);
            criteriaQuery.distinct(true);
            Predicate checkUser = criteriaBuilder.equal(root.get("user"), user.getId());
            return session.createQuery(criteriaQuery.where(checkUser)).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get all orders by user " + user, e);
        }
    }

    @Override
    public void add(Order order) {
        super.addAbstract(order);
    }
}
