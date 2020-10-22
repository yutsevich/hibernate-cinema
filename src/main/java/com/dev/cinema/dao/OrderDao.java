package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao {
    List<Order> getByUser(User user);

    void add(Order order);
}
