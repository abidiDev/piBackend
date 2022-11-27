package com.spring.pi.services;


import com.spring.pi.payload.request.LoginRequest;
import com.spring.pi.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {


    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) ;

}
