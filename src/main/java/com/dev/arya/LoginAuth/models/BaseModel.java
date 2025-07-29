package com.dev.arya.LoginAuth.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;


@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
