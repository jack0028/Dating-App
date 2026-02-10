package com.dating.Store;

import java.util.*;
import org.springframework.stereotype.Component;

import com.dating.Entity.User;

import jakarta.annotation.PostConstruct;

@Component
public class InMemoryUserStore {

    private final Map<Long, User> users = new HashMap<>();

    @PostConstruct
    public void loadData() {

        users.put(1L, new User(
                1L, "User 1", 25, "Female",
                List.of("Cricket", "Chess")));

        users.put(2L, new User(
                2L, "User 2", 27, "Male",
                List.of("Cricket", "Football", "Movies")));

        users.put(3L, new User(
                3L, "User 3", 26, "Male",
                List.of("Movies", "Tennis", "Football", "Cricket")));

        users.put(4L, new User(
                4L, "User 4", 24, "Female",
                List.of("Tennis", "Badminton", "Football")));

        users.put(5L, new User(
                5L, "User 5", 32, "Female",
                List.of("Cricket", "Football", "Movies", "Badminton")));
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void save(User user) {
        users.put(user.getId(), user);
    }
}

