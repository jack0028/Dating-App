package com.dating.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.Entity.User;
import com.dating.Store.InMemoryUserStore;

@Service
public class UserService {

    @Autowired
    private InMemoryUserStore userStore;

    @Autowired
    private RecommendationEngine recommendationEngine;

    public List<User> recommendMatches(Long userId, int topN) {

        User source = userStore.findById(userId);

        if (source == null) {
            throw new IllegalArgumentException("User not found");
        }

        List<User> candidates = userStore.findAll()
                .stream()
                .filter(u -> !u.getGender().equals(source.getGender()))
                .filter(u -> !u.getId().equals(source.getId()))
                .toList();

        return recommendationEngine.findTopMatches(source, candidates, topN);
    }

    public List<User> getAllUsers() {
        return userStore.findAll();
    }

    public void saveUser(User user) {
        userStore.save(user);
    }

    public User findUserById(Long id) {

        User user = userStore.findById(id);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return user;
    }
}
