package com.spring.pi.services;


import com.spring.pi.entities.Actor;
import com.spring.pi.payload.request.LoginRequest;
import com.spring.pi.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {


    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) ;
    public void updateResetPasswordToken(String token, String email) throws Exception;
    public Actor getByResetPasswordToken(String token);
    public void updatePassword(Actor actor, String newPassword);
    public ResponseEntity<?> loginWithGoogle( String tokenDto)throws Exception;
    public ResponseEntity<?> loginWithFacebook( String tokenDto) throws Exception ;

}
