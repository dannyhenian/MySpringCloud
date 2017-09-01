package com.danny.cloud.auth;

import com.danny.cloud.user.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
