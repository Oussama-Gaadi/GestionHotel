package com.gestion_hotel.demo.repositories;

import jakarta.transaction.Transactional;
import com.gestion_hotel.demo.entities.Chambre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ChambreRepository extends JpaRepository<Chambre, Integer> {

    List<Chambre> findByCodeChambre(String name);

    List<Chambre> findByCodeChambreContains(String name);

    Page<Chambre> findByCodeChambreContains(String name, PageRequest pageable);

}
