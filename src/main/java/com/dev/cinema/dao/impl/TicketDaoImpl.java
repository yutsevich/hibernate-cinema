package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Ticket add(Ticket ticket) {
        return super.create(ticket);
    }
}
