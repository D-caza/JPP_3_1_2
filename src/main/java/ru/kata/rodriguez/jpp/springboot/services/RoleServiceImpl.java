package ru.kata.rodriguez.jpp.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.rodriguez.jpp.springboot.repositories.RoleRepository;
import ru.kata.rodriguez.jpp.springboot.models.Role;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}