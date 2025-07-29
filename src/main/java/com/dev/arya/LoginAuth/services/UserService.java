package com.dev.arya.LoginAuth.services;

import com.dev.arya.LoginAuth.dtos.UserDto;
import com.dev.arya.LoginAuth.models.Role;
import com.dev.arya.LoginAuth.models.User;
import com.dev.arya.LoginAuth.repository.UserRepository;
import com.dev.arya.LoginAuth.repository.roleRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private roleRepository roleRepository;

    public UserService(UserRepository userRepository,roleRepository roleRepository){
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
