package com.dating.rule;

import org.springframework.stereotype.Component;

import com.dating.Entity.User;

@Component
public class AgeRule implements MatchingRule {
    @Override
    public int compare(User source, User u1, User u2) {
        int ageDiff1 = Math.abs(source.getAge() - u1.getAge());
        int ageDiff2 = Math.abs(source.getAge() - u2.getAge());
        return Integer.compare(ageDiff1, ageDiff2);
    }
}

