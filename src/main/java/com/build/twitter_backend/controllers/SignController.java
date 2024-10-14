package com.build.twitter_backend.controllers;

import com.build.twitter_backend.dtos.JwtTokenDto;
import com.build.twitter_backend.dtos.SigninDto;
import com.build.twitter_backend.dtos.SignupDto;
import com.build.twitter_backend.entities.User;
import com.build.twitter_backend.services.TokenService;
import com.build.twitter_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noauth")
public class SignController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid SigninDto signinDto) throws Exception {
        var authToken = new UsernamePasswordAuthenticationToken(signinDto.username(), signinDto.password());
        var auth = authenticationManager.authenticate(authToken);

        var jwtToken = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new JwtTokenDto(jwtToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody @Valid SignupDto payload) throws Exception {
        var userDto = new User();

        userDto.setUsername(payload.username());
        userDto.setPassword(passwordEncoder.encode(payload.password()));

        var newUser = service.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
