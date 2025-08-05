package com.dev.arya.LoginAuth2.dtos;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class setUserRolesRequestDto {

    private List<Long> roleIds;
}
