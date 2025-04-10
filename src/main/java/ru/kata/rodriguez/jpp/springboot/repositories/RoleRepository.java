package ru.kata.rodriguez.jpp.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.rodriguez.jpp.springboot.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}