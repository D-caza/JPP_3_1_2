package ru.kata.rodriguez.jpp.springboot.services;

import ru.kata.rodriguez.jpp.springboot.models.Role;

public interface RoleService {
    void save(Role role);
    Role getRoleById(int id);
    void deleteRoleById(int id);
    void deleteRole(Role role);
}