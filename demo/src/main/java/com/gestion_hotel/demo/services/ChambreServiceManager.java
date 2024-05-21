package com.gestion_hotel.demo.services;

import com.gestion_hotel.demo.entities.Chambre;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface ChambreServiceManager {

    Page<Chambre> getAllChambres(int page, int size, String searchName);

    Optional<Chambre> getChambreById(Integer id);

    Chambre saveOrUpdateChambre(Chambre chambre);

    void deleteChambre(Integer id);
}
