package com.dev.arya.LoginAuth2.services;

import com.dev.arya.LoginAuth2.dtos.UserDto;
import com.dev.arya.LoginAuth2.models.Role;
import com.dev.arya.LoginAuth2.models.User;
import com.dev.arya.LoginAuth2.repository.UserRepository;
import com.dev.arya.LoginAuth2.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }
    public UserDto getUserDetails(Long userId){
        System.out.println("I got the request");
        return new UserDto();
    }

    public UserDto setUserRoles(Long userId, List<Long> roleIds){
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role>roles=roleRepository.findAllByIdIn(roleIds);

        if(userOptional.isEmpty()){
            return null;
        }
        User user=userOptional.get();
        User savedUser=userRepository.save(user);
        return UserDto.from(savedUser);
    }
}
