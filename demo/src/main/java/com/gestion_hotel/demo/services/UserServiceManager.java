package com.gestion_hotel.demo.services;

import com.gestion_hotel.demo.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserServiceManager {

    List<User> getAllUsers();

    Optional<User> getUserById(Integer id);

    User saveOrUpdateUser(User user);

    void deleteUser(Integer id);

    Optional<User> findByUsername(String username);
}
