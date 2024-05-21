package com.gestion_hotel.demo.web;

import com.gestion_hotel.demo.entities.Reservation;
import com.gestion_hotel.demo.entities.User;
import com.gestion_hotel.demo.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private HttpSession session;

    public User checkLogin() {
        User user = (User) session.getAttribute("loggedInUser");
        return user;
    }

    @GetMapping(path = "/reservations")
    public String allReservations(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size,
                                  @RequestParam(name = "search", defaultValue = "") String searchName) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        Page<Reservation> pageReservations = reservationRepository.findByUserUsernameContains(searchName,
                PageRequest.of(page, size));

        int[] pages = new int[pageReservations.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("pageReservations", pageReservations.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        model.addAttribute("is_admin", user.getIsAdmin());
        return "reservations";
    }

    @GetMapping(path = "/reservations/create")
    public String createReservation(Model model) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        model.addAttribute("is_admin", user.getIsAdmin());

        return "formAddReservation";
    }

    @PostMapping(path = "/reservations/save")
    public String saveReservation(Model model, Reservation reservation,
                                  @RequestParam(name = "currentPage", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size,
                                  @RequestParam(name = "searchName", defaultValue = "") String search) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        reservationRepository.save(reservation);

        model.addAttribute("is_admin", user.getIsAdmin());

        return "redirect:/reservations?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/reservations/delete")
    public String deleteReservation(Model model,
                                    int page, int size, String search,
                                    @RequestParam(name = "id") Integer id) {
        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        reservationRepository.deleteById(id);

        model.addAttribute("is_admin", user.getIsAdmin());

        return "redirect:/reservations?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/reservations/edit")
    public String editReservation(Model model, int page, int size, String search, Integer id) {
        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        model.addAttribute("is_admin", user.getIsAdmin());

        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null)
            throw new RuntimeException("Erreur");
        model.addAttribute("reservation", reservation);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        model.addAttribute("is_admin", user.getIsAdmin());

        return "formEditReservation";
    }
}
