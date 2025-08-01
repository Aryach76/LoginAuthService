package com.dev.arya.LoginAuth.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String Email;
    private String Password;
}
