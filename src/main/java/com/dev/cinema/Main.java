package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        UserService userService
                = (UserService) injector.getInstance(UserService.class);
        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("user1@gmail.com", "pass");
        authenticationService.register("user2@gmail.com", "pass");
        System.out.println(userService.findByEmail("user1@gmail.com").toString());
        try {
            System.out.println(authenticationService.login("user2@gmail.com", "pass").toString());
        } catch (AuthenticationException e) {
            e.getMessage();
        }
    }
}
