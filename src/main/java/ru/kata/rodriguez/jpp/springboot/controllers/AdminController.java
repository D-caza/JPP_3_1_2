package ru.kata.rodriguez.jpp.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.rodriguez.jpp.springboot.models.Role;
import ru.kata.rodriguez.jpp.springboot.models.User;
import ru.kata.rodriguez.jpp.springboot.services.RegistrationService;
import ru.kata.rodriguez.jpp.springboot.services.RoleService;
import ru.kata.rodriguez.jpp.springboot.services.UserService;
import ru.kata.rodriguez.jpp.springboot.util.UserValidate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidate userValidate;
    private final RegistrationService registrationService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidate userValidate, RegistrationService registrationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidate = userValidate;
        this.registrationService = registrationService;
    }

    @GetMapping()
    public String usersPage(Model model) {
        model.addAttribute("listUser", userService.index());
        model.addAttribute("thisUser", userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        return "admin/users";
    }

    @GetMapping("/show")
    public String show(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("thisUser", userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        userValidate.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "admin/new";

        registrationService.register(user);
        return "redirect:admin";
    }

    @PutMapping("/edit")
    public String update(@RequestParam(value = "id") int id, String firstName, String lastName, String email, String role, String password) {
        User user = new User();
        user.setPassword(password.length() < 3 ? userService.show(id).getPassword() : registrationService.encodePassword(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        Role newRole = new Role("ROLE_"+role);
        user.setRoles(Collections.singleton(newRole));
        roleService.save(newRole);
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}