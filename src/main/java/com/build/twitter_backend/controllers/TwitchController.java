package com.build.twitter_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/twitches")
public class TwitchController {
    @GetMapping
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("Hello, world!");
    }
}
