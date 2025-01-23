package com.dating.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dating.Entity.User;
import com.dating.Rule.MatchingRule;

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
                .collect(Collectors.toList());
    }
}
