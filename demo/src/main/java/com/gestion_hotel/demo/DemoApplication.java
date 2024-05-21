package com.gestion_hotel.demo;

import com.gestion_hotel.demo.entities.Chambre;
import com.gestion_hotel.demo.entities.User;
import com.gestion_hotel.demo.repositories.ChambreRepository;
import com.gestion_hotel.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	ChambreRepository chambreRepository;
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		chambreRepository.save(new Chambre(null, "R103", 250.00, 5));
		chambreRepository.save(new Chambre(null, "R105", 210.00, 4));
		chambreRepository.save(new Chambre(null, "R201", 175.00, 4));
		chambreRepository.save(new Chambre(null, "R305", 129.99, 3));
		chambreRepository.save(new Chambre(null, "R402", 315.00, 5));
		chambreRepository.save(new Chambre(null, "R510", 225.00, 4));
		chambreRepository.save(new Chambre(null, "R615", 154.95, 3));
		userRepository.save(new User(null, "admin", "admin", true));

		List<Chambre> chambres = chambreRepository.findAll();
		chambres.forEach(System.out::println);

		List<Chambre> pageChambres = chambreRepository.findByCodeChambreContains("ah");

		pageChambres.forEach(System.out::println);
	}
}
