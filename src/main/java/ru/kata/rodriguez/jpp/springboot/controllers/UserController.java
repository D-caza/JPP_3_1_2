package ru.kata.rodriguez.jpp.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.rodriguez.jpp.springboot.models.User;
import ru.kata.rodriguez.jpp.springboot.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String userPage(@ModelAttribute("user") User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = userService.findByName(authentication.getName()).get();
        user.setFirstName(userDetails.getFirstName());
        user.setId(userDetails.getId());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        return "user";
    }

    @PostMapping("/logout")
    public String logoutPage() {
        return "redirect:/auth/login";
    }
}