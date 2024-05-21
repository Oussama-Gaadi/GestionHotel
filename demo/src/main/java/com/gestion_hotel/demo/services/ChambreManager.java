package com.gestion_hotel.demo.services;

import com.gestion_hotel.demo.entities.Chambre;
import com.gestion_hotel.demo.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChambreManager implements ChambreServiceManager {

    private ChambreRepository chambreRepository;

    @Autowired
    public void ChambreServiceManager(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    public ChambreManager(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    @Override
    public Page<Chambre> getAllChambres(int page, int size, String searchName) {
        return chambreRepository.findByCodeChambreContains(searchName, PageRequest.of(page, size));
    }

    @Override
    public Optional<Chambre> getChambreById(Integer id) {
        return chambreRepository.findById(id);
    }

    @Override
    public Chambre saveOrUpdateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(Integer id) {
        chambreRepository.deleteById(id);
    }
}
