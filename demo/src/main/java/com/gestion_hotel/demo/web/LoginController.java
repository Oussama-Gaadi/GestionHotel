package com.gestion_hotel.demo.web;

import com.gestion_hotel.demo.entities.User;
import com.gestion_hotel.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private HttpSession session;

    public User checkLogin() {
        User user = (User) session.getAttribute("loggedInUser");
        return user;
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        if (checkLogin() != null) {
            return "redirect:/";
        }
        try {
            User user = userRepository.findByUsernameAndPassword(username, password).get(0);

            if (user != null) {
                session.setAttribute("loggedInUser", user);
                return "redirect:/";
            } else {
                return "redirect:/login";
            }
        } catch (Exception e) {
            return "redirect:/login";

        }
    }

    @GetMapping(path = "/login")
    public String submitLogin() {
        if (checkLogin() != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(path = "/logout")
    public String logout(Model model) {
        session.invalidate();
        return "/login";
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        if (checkLogin() != null) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping(path = "/register")
    public String sumbitRegister(@RequestParam String username, @RequestParam String password) {
        if (checkLogin() != null) {
            return "redirect:/";
        }

        if (userRepository.findByUsername(username).size() > 0) {
            return "redirect:/register";
        }

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setIsAdmin(true);

        userRepository.save(user);

        return "redirect:/login";
    }

}
