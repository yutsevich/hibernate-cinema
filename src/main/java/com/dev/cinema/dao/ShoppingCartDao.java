package com.dev.cinema.dao;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

    ShoppingCart add(ShoppingCart shoppingCart);
}
