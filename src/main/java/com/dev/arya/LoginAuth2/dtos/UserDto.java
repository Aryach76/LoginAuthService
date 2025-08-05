package com.dev.arya.LoginAuth2.dtos;

import com.dev.arya.LoginAuth2.models.Role;
import com.dev.arya.LoginAuth2.models.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private Set<Role>roles=new HashSet<>();

    public static UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
       // userDto.setRoles(user.getRoles());
        return userDto;
    }
}
