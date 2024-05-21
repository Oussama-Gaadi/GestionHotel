package com.gestion_hotel.demo.repositories;

import jakarta.transaction.Transactional;
import com.gestion_hotel.demo.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(Integer userId);

    List<Reservation> findByChambreId(Integer chambreId);

    List<Reservation> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Page<Reservation> findByStatusContains(String status, PageRequest pageable);

    Page<Reservation> findByUserUsernameContains(String searchName, PageRequest of);
}
