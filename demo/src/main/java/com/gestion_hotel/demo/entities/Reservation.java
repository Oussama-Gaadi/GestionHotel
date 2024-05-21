package com.gestion_hotel.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "startDate", nullable = false)
    @NotNull
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    @NotNull
    private Date endDate;

    @Column(name = "status", nullable = false)
    @NotEmpty
    private String status;

    @Column(name = "totalPrice", nullable = false)
    @DecimalMin(value = "0.01")
    private Double totalPrice;

    @Column(name = "bookingDate", nullable = false)
    @NotNull
    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "chambre_id", nullable = false)
    private Chambre chambre;
}
