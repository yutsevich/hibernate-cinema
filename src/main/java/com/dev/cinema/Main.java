package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Fast & Furious");
        movie1.setDescription("action");
        movieService.add(movie1);
        movieService.getAll().forEach(System.out::println);
        Movie movie2 = new Movie();
        movie2.setTitle("Matrix");
        movie2.setDescription("Science fiction");
        movieService.add(movie2);
        movieService.getAll().forEach(System.out::println);
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100L);
        cinemaHall.setDescription("normal");
        cinemaHallService.add(cinemaHall);
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(35L);
        cinemaHall1.setDescription("luxury");
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.getAll().forEach(System.out::println);
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie2);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 20)));
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall);
        movieSession2.setMovie(movie1);
        movieSession2.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 55)));
        MovieSession movieSession3 = new MovieSession();
        movieSession3.setCinemaHall(cinemaHall1);
        movieSession3.setMovie(movie2);
        movieSession3.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 20)));
        MovieSession movieSession4 = new MovieSession();
        movieSession4.setCinemaHall(cinemaHall);
        movieSession4.setMovie(movie2);
        movieSession4.setShowTime(LocalDateTime.of(LocalDate.now().plusDays(1),
                LocalTime.of(0, 0)));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession2);
        movieSessionService.add(movieSession3);
        movieSessionService.add(movieSession4);
        movieSessionService.findAvailableSessions(2L, LocalDate.now())
                .forEach(System.out::println);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User visitor = new User();
        visitor.setEmail("jackie@yandex.com");
        visitor.setPassword("p@ssw0rd");
        userService.add(visitor);
        User buyer = new User();
        buyer.setEmail("texas.ranger@gmail.com");
        buyer.setPassword("M@rtia1Arts");
        userService.add(buyer);
        User explorer = new User();
        explorer.setEmail("diagram@mit.com");
        explorer.setPassword("N0be1Prize");
        userService.add(explorer);
        System.out.println(userService.findByEmail("diagram@mit.com"));
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("ranger@gmail.com", "M@rtia1Arts");
        System.out.println(authenticationService.login("texas.ranger@gmail.com", "M@rtia1Arts"));

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        User testShoppingCart = userService.findByEmail("ranger@gmail.com").get();
        ShoppingCart userShoppingCart = shoppingCartService.getByUser(testShoppingCart);
        System.out.println(userShoppingCart);
        shoppingCartService.addSession(movieSession2, testShoppingCart);
        shoppingCartService.addSession(movieSession4, testShoppingCart);
        System.out.println(shoppingCartService.getByUser(testShoppingCart));
        shoppingCartService.clear(userShoppingCart);
        System.out.println(userShoppingCart);

        User user1 = authenticationService.register("user1@gmail.com", "pass");
        User user2 = authenticationService.register("user2@gmail.com", "pass");
        System.out.println(userService.findByEmail("user1@gmail.com").toString());
        try {
            System.out.println(authenticationService.login("user2@gmail.com", "pass").toString());
        } catch (AuthenticationException e) {
            e.getMessage();
        }
        OrderService orderService
                = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user1));
        orderService.getOrderHistory(user1).forEach(System.out::println);
    }
}
