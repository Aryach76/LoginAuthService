package com.dev.arya.LoginAuth.repository;

import com.dev.arya.LoginAuth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findAllByIdIn(List<Long> roleIds);
}
