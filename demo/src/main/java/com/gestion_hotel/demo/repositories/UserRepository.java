package com.gestion_hotel.demo.repositories;

import jakarta.transaction.Transactional;
import com.gestion_hotel.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsernameAndPassword(String username, String password);

    List<User> findByUsername(String username);

}
