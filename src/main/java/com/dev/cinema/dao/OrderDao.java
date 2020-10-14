package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order add(List<Ticket> tickets, User user);

    List<Order> getByUser(User user);
}
