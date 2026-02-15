package com.dating.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.dating.entity.User;
import com.dating.entity.Gender;

@Service
public class UserService {
    // ✅ Define constants for repeated literals
    private static final String CRICKET = "Cricket";
    private static final String FOOTBALL = "Football";
    private static final String MOVIES = "Movies";
    private static final String TENNIS = "Tennis";
    private static final String BADMINTON = "Badminton";
    private static final String CHESS = "Chess";

    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final RecommendationEngine recommendationEngine;

    public UserService(RecommendationEngine recommendationEngine) {
        this.recommendationEngine = recommendationEngine;
        loadSampleData(); // preload sample users
    }

    private void loadSampleData() {
        saveUser(new User(null, Gender.FEMALE, 25, Arrays.asList(CRICKET, CHESS)));
        saveUser(new User(null, Gender.MALE, 27, Arrays.asList(CRICKET, FOOTBALL, MOVIES)));
        saveUser(new User(null, Gender.MALE, 26, Arrays.asList(MOVIES, TENNIS, FOOTBALL, CRICKET)));
        saveUser(new User(null, Gender.FEMALE, 24, Arrays.asList(TENNIS, FOOTBALL, BADMINTON)));
        saveUser(new User(null, Gender.FEMALE, 32, Arrays.asList(CRICKET, FOOTBALL, MOVIES, BADMINTON)));
    }

    public List<User> recommendMatches(Long userId, int topN) {
        User source = findUserById(userId);
        List<User> candidates = users.stream()
                .filter(u -> !u.getId().equals(source.getId()) && !u.getGender().equals(source.getGender()))
                .toList(); // ✅ modern replacement for Collectors.toList()
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
