package com.dating.Rule;

import com.dating.Entity.User;
public interface MatchingRule {
    int compare(User source, User u1, User u2);
}
