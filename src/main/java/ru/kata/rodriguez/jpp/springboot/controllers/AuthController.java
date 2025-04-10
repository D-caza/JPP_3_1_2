package ru.kata.rodriguez.jpp.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.rodriguez.jpp.springboot.models.User;
import ru.kata.rodriguez.jpp.springboot.services.RegistrationService;
import ru.kata.rodriguez.jpp.springboot.services.UserService;
import ru.kata.rodriguez.jpp.springboot.util.UserValidate;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserValidate userValidate;
    private RegistrationService registrationService;
    private UserService userService;

    @Autowired
    public AuthController(UserValidate userValidate, RegistrationService registrationService, UserService userService) {
        this.userValidate = userValidate;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidate.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(user);
        return "redirect:/admin";
    }
}