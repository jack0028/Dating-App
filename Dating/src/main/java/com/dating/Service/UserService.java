package com.dating.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dating.Entity.User;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final RecommendationEngine recommendationEngine;

    public UserService(RecommendationEngine recommendationEngine) {
        this.recommendationEngine = recommendationEngine;
        loadSampleData(); // preload sample users
    }

    private void loadSampleData() {
        saveUser(new User(null, "Female", 25, Arrays.asList("Cricket", "Chess")));
        saveUser(new User(null, "Male", 27, Arrays.asList("Cricket", "Football", "Movies")));
        saveUser(new User(null, "Male", 26, Arrays.asList("Movies", "Tennis", "Football", "Cricket")));
        saveUser(new User(null, "Female", 24, Arrays.asList("Tennis", "Football", "Badminton")));
        saveUser(new User(null, "Female", 32, Arrays.asList("Cricket", "Football", "Movies", "Badminton")));
    }

    public List<User> recommendMatches(Long userId, int topN) {
        User source = findUserById(userId);
        List<User> candidates = users.stream()
                .filter(u -> !u.getId().equals(source.getId()) && !u.getGender().equals(source.getGender()))
                .collect(Collectors.toList());
        return recommendationEngine.findTopMatches(source, candidates, topN);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void saveUser(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        users.add(user);
    }

    public User findUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
