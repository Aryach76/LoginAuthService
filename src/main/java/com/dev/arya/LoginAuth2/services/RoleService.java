package com.dev.arya.LoginAuth2.services;

import com.dev.arya.LoginAuth2.models.Role;
import com.dev.arya.LoginAuth2.repository.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role createRole(String name){
        Role role=new Role();
        role.setRole(name);
        return roleRepository.save(role);
    }
}
