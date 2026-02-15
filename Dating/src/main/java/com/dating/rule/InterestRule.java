package com.dating.rule;

import org.springframework.stereotype.Component;

import com.dating.entity.User;

@Component
public class InterestRule implements MatchingRule {
    @Override
    public int compare(User source, User u1, User u2) {
        long matches1 = source.getInterests().stream()
                              .filter(u1.getInterests()::contains)
                              .count();
        long matches2 = source.getInterests().stream()
                              .filter(u2.getInterests()::contains)
                              .count();
        return Long.compare(matches2, matches1);
    }
}


