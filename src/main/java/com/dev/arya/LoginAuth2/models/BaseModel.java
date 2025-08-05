package com.dev.arya.LoginAuth2.models;

import jakarta.persistence.*;


@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
