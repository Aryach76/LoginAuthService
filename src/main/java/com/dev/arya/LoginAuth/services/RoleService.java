package com.dev.arya.LoginAuth.services;

import com.dev.arya.LoginAuth.dtos.CreateRoleRequestDto;
import com.dev.arya.LoginAuth.models.Role;
import com.dev.arya.LoginAuth.repository.RoleRepository;
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
