package com.gestion_hotel.demo.services;

import com.gestion_hotel.demo.entities.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationServiceManager{

    List<Reservation> getAllReservations();

    Optional<Reservation> getReservationById(Integer id);

    Reservation saveOrUpdateReservation(Reservation reservation);

    void deleteReservation(Integer id);
}
