package com.build.twitter_backend.controllers;

import com.build.twitter_backend.dtos.SigninDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid SigninDto signinDto) throws Exception {
        var token = new UsernamePasswordAuthenticationToken(signinDto.username(), signinDto.password());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
