package ru.kata.rodriguez.jpp.springboot.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.rodriguez.jpp.springboot.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> index();
    User show(int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
    Optional<User> findByName(String name);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}