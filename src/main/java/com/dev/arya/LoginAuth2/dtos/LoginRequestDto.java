package com.dev.arya.LoginAuth2.dtos;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String Email;
    private String Password;

}

