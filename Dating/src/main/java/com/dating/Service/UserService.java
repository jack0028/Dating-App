package com.dating.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.Entity.User;
import com.dating.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecommendationEngine recommendationEngine;

    public List<User> recommendMatches(Long userId, int topN) {
        User source = userRepository.findById(userId)
                                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<User> candidates = userRepository.findAllByGenderNotAndIdNot(source.getGender(), source.getId());
        System.out.println("RE :"+candidates);       
        return recommendationEngine.findTopMatches(source, candidates, topN);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user); 
    }
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
