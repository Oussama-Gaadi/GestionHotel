package com.gestion_hotel.demo.web;

import com.gestion_hotel.demo.entities.User;
import com.gestion_hotel.demo.entities.Chambre;
import com.gestion_hotel.demo.repositories.ChambreRepository;
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
public class ChambreController {

    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    private HttpSession session;

    public User checkLogin() {
        User user = (User) session.getAttribute("loggedInUser");
        return user;
    }

    @GetMapping(path = "/index")
    public String allChambres(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "search", defaultValue = "") String searchName) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        Page<Chambre> pageChambres = chambreRepository.findByCodeChambreContains(searchName,
                PageRequest.of(page, size));

        int[] pages = new int[pageChambres.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("pageChambres", pageChambres.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        model.addAttribute("is_admin", user.getIsAdmin());
        return "chambres";
    }

    @GetMapping(path = "/create")
    public String createChambre(Model model) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        Chambre Chambre = new Chambre();
        model.addAttribute("chambre", Chambre);
        model.addAttribute("is_admin", user.getIsAdmin());

        return "formAddChambre";

    }

    @PostMapping(path = "/save")
    public String saveChambre(Model model, Chambre s,
            @RequestParam(name = "currentPage", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "searchName", defaultValue = "") String search) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        chambreRepository.save(s);

        model.addAttribute("is_admin", user.getIsAdmin());

        return "redirect:/index?page=" + page + "&size=" + size + "&search=" + search;

    }

    @GetMapping(path = "/")
    public String homePage(Model model) {

        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        model.addAttribute("is_admin", user.getIsAdmin());

        return "/menu";
    }

    @GetMapping(path = "/delete")
    public String deleteChambre(Model model,
            int page, int size, String search,
            @RequestParam(name = "id") Integer id) {
        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        chambreRepository.deleteById(id);

        model.addAttribute("is_admin", user.getIsAdmin());

        return "redirect:/index?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/edit")
    public String editChambre(Model model, int page, int size, String search, Integer id) {
        User user = checkLogin();
        if (user == null || !user.isIsAdmin()) {
            return "redirect:/login";
        }

        model.addAttribute("is_admin", user.getIsAdmin());

        Chambre Chambre = chambreRepository.findById(id).orElse(null);
        if (Chambre == null)
            throw new RuntimeException("Erreur");
        model.addAttribute("chambre", Chambre);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        model.addAttribute("is_admin", user.getIsAdmin());

        return "formEditChambre";

    }

}
