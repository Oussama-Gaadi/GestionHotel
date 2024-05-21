package com.gestion_hotel.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "chambres")
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codeChambre", nullable = false)
    @NotEmpty
    @Size(min = 5, max = 30)
    private String codeChambre;

    @Column(name = "prix", nullable = false)
    @DecimalMin(value = "0.01")
    public Double prix;

    @Column(name = "star", nullable = false)
    @Min(value = 1 )
    @Max(value = 5)
    private Integer star;

}
