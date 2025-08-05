package com.dev.arya.LoginAuth2.dtos;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequestDto {

    private String token;
    private Long userId;

}
