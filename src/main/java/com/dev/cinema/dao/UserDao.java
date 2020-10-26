package com.dev.cinema.dao;

import com.dev.cinema.model.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);

    User add(User user);

    Optional<User> get(Long id);
}
