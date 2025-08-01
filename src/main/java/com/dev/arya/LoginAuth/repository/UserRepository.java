package com.dev.arya.LoginAuth.repository;

import com.dev.arya.LoginAuth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
}
