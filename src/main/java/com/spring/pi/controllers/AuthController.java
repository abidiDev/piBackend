package com.spring.pi.controllers;

import javax.validation.Valid;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.notMapped.EmailDetails;
import com.spring.pi.payload.request.LoginRequest;
import com.spring.pi.payload.request.SignupRequest;
import com.spring.pi.services.AuthService;
import com.spring.pi.services.ContactingService;
import lombok.AllArgsConstructor;
import lombok.Value;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

;import java.util.Collection;
import java.util.Collections;
import java.util.Map;
;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
  AuthService authService;
  JavaMailSender mailSender;
  ContactingService contactingService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    return authService.authenticateUser(loginRequest);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
  }


  @GetMapping("/forgot_passworddemond/{email}")
  public String forgotPasswordDemond(@PathVariable String email) {
    String token = RandomString.make(30);
    String msg;


    try {
      authService.updateResetPasswordToken(token, email);
      String resetPasswordLink =  "http://4200/reset_password?token=" + token;
      EmailDetails ed=new EmailDetails();
      ed.setRecipient(email);
      ed.setSubject("Reset password Link from Tsamsira");
      String content= "<html>"
              + "<body>"
              + "<div style=\"background-color: #f9f9f9; padding: 20px;\">"
              + "<img src=\"https://zupimages.net/up/23/09/am8j.png\"  style=\"display: block; margin: 0 auto;\" />"
              + "<p>Hello,</p>"
              + "<p>You have requested to reset your password.</p>"
              + "<p>Click the link below to change your password:</p>"
              + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
              + "<br>"
              + "<p>Ignore this email if you do remember your password, "
              + "or you have not made the request.</p>"
              + "</div>"
              + "</body>"
              + "</html>";
      ed.setMsgBody(content);
      contactingService.sendSimpleMail(ed);
      msg="We have sent a reset password link to your email. Please check.";

    } catch (Exception ex) {
      msg=ex.getMessage();


    }

    return msg;
  }



  @GetMapping("/reset_password/{token}/{password}")
  public String processResetPassword(@PathVariable String token,@PathVariable String password) {
    String msg;
    Actor actor = authService.getByResetPasswordToken(token);

    if (actor == null) {
      msg="Invalid Token";
    } else {
      authService.updatePassword(actor, password);

      msg="Password updated successfully";

    }

    return msg;
  }
  /**************************google auth*****************************************************/
  //http://localhost:8080/social/google
  @GetMapping("/google/{token}")
  public ResponseEntity<?> loginWithGoogle(@PathVariable String token) throws Exception {
    return authService.loginWithGoogle(token);
  }
  @GetMapping("/facebook/{token}")

  public ResponseEntity<?> loginWithFacebook( String tokenDto) throws Exception {
    return authService.loginWithGoogle(tokenDto);


  }

}

