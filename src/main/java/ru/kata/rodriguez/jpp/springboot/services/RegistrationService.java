package ru.kata.rodriguez.jpp.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.rodriguez.jpp.springboot.repositories.RoleRepository;
import ru.kata.rodriguez.jpp.springboot.repositories.UserRepository;
import ru.kata.rodriguez.jpp.springboot.models.Role;
import ru.kata.rodriguez.jpp.springboot.models.User;

import java.util.Collections;

@Service
public class RegistrationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}