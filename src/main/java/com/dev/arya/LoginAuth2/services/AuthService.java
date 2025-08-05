package com.dev.arya.LoginAuth2.services;

import com.dev.arya.LoginAuth2.dtos.UserDto;
import com.dev.arya.LoginAuth2.models.Session;
import com.dev.arya.LoginAuth2.models.SessionStatus;
import com.dev.arya.LoginAuth2.models.User;
import com.dev.arya.LoginAuth2.repository.SessionRepository;
import com.dev.arya.LoginAuth2.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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

        if(!bCryptPasswordEncoder.matches(password,user.getPassword()))
            throw new RuntimeException("Wrong username Password ");


     //   String token= RandomStringUtils.randomAlphanumeric(30);

        MacAlgorithm alg= Jwts.SIG.HS256;
        SecretKey key=alg.key().build();

        String message="{\n" +
                "  \"email\":\"test@scaler.com\",\n" +
                "  \"roles\":[\n" +
                "  \"mentor\",\n" +
                "  \"ta\"\n" +
                "  ],\n" +
                "  \"expirationDate\":\"23rdOctober2023\"\n" +
                "}";
        byte[] content= message.getBytes(StandardCharsets.UTF_8);

        String token=Jwts.builder().content(content,"text/plain").signWith(key,alg).compact();


        Session session=new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);


        MultiValueMapAdapter<String,String> headers=new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"Auth-token"+token);

        UserDto userDto=UserDto.from(user);

        ResponseEntity<UserDto> response= new ResponseEntity<>(userDto,headers,HttpStatus.OK);
        return response;

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
