package com.danny.cloud.security;

import java.util.Optional;

import com.danny.cloud.entity.User;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UserService {
    public Optional<User> getByUsername(String username);
}
