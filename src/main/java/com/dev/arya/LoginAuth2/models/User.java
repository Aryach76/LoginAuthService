package com.dev.arya.LoginAuth2.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonDeserialize(as=User.class)
public class User extends BaseModel{


    private String email;
    private String Password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore

    private Set<Role> roles= new HashSet<>();

}
