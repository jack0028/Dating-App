package com.dating.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dating.entity.User;
import com.dating.rule.MatchingRule;

@Service
public class RecommendationEngine {
    private final List<MatchingRule> rules;

    public RecommendationEngine(List<MatchingRule> rules) {
        this.rules = rules;
    }

    public List<User> findTopMatches(User source, List<User> candidates, int topN) {
        return candidates.stream()
                .sorted((u1, u2) -> {
                    for (MatchingRule rule : rules) {
                        int comparison = rule.compare(source, u1, u2);
                        if (comparison != 0) {
                            return comparison;
                        }
                    }
                    return 0;
                })
                .limit(topN)
                .toList();
    }
}






