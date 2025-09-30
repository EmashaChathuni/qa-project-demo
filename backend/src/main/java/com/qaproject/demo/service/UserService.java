package com.qaproject.demo.service;

import com.qaproject.demo.entity.User;
import com.qaproject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return false; }
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        try {Optional<User> userOpt = userRepository.findByUsername(username.trim());
      if (userOpt.isPresent()) {
                User user = userOpt.get();
                return user.getPassword() != null &&
                        user.getPassword().equals(password);
            }
            return false;

        } catch (Exception e) {
           
            return false;
        }
    }
}