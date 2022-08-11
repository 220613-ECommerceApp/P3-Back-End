package com.revature.services;

import java.util.Optional;
import com.revature.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public Optional<User> findByCredentials(String email, String password) {
        return userService	.findByCredentials(email, password);
    }

    public User register(User user) {
        return userService.save(user);
    }
}
