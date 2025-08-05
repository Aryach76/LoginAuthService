package com.dev.arya.LoginAuth2.dtos;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateTokenRequestDto {

    private String token;
    private Long UserId;
}
