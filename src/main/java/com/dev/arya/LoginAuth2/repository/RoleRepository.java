package com.dev.arya.LoginAuth2.repository;

import com.dev.arya.LoginAuth2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findAllByIdIn(List<Long> roleIds);
}
