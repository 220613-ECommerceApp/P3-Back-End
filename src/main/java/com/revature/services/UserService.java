package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    
    
    public User getByUsername(String name) {
    	return userRepository.getByUsername(name);
    }
    
    public User getId(int id) {
    	return userRepository.findById(id);
    }
    
    public User update(User u) {
    	return userRepository.save(u);
    }
}
