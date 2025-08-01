package com.dev.arya.LoginAuth.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {

    private String token;
    private Long UserId;
}
