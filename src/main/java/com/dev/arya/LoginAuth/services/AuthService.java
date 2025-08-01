package com.dev.arya.LoginAuth.services;

import com.dev.arya.LoginAuth.dtos.UserDto;
import com.dev.arya.LoginAuth.models.Session;
import com.dev.arya.LoginAuth.models.SessionStatus;
import com.dev.arya.LoginAuth.models.User;
import com.dev.arya.LoginAuth.repository.SessionRepository;
import com.dev.arya.LoginAuth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository,SessionRepository sessionRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.sessionRepository=sessionRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }


    public ResponseEntity<UserDto> login(String email,String password){
        Optional<User> userOptional=userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            return null;
        }
        User user=userOptional.get();
        return null;
    }

    public ResponseEntity<Void> logout(String token,Long userId){
        Optional<Session> sessionOptional= sessionRepository.findByTokenAndUser_Id(token,userId);

        if(sessionOptional.isEmpty())
            return null;

        Session session=sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public UserDto signup(String Email,String Password){
            User user=new User();
            user.setEmail(Email);
            user.setPassword(bCryptPasswordEncoder.encode(Password));

            User savedUser=userRepository.save(user);

            UserDto userDto=UserDto.from(savedUser);

            return userDto;
    }

    public SessionStatus validate(String token,Long UserId){
        Optional<Session> sessionOptional=sessionRepository.findByTokenAndUser_Id(token,UserId);
        if(sessionOptional.isEmpty()) {
            return SessionStatus.ENDED;
        }
        Session session=sessionOptional.get();

        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)){
            return SessionStatus.ENDED;
        }

        return SessionStatus.ACTIVE;
    }
}
