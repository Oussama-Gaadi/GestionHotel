package com.gestion_hotel.demo.services;

import com.gestion_hotel.demo.entities.User;
import com.gestion_hotel.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    private UserRepository userRepository;

    @Autowired
    public void UserServiceManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}