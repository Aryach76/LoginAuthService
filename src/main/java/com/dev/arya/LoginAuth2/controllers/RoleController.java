package com.dev.arya.LoginAuth2.controllers;


import com.dev.arya.LoginAuth2.dtos.CreateRoleRequestDto;
import com.dev.arya.LoginAuth2.models.Role;
import com.dev.arya.LoginAuth2.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(CreateRoleRequestDto request){
        Role role=roleService.createRole(request.getName());
        return new ResponseEntity<>(role,HttpStatus.OK);
    }
}
