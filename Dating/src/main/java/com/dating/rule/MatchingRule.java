package com.dating.rule;

import com.dating.entity.User;
public interface MatchingRule {
    int compare(User source, User u1, User u2);
}

