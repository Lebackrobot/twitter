package com.build.twitter_backend.controllers;

import com.build.twitter_backend.entities.User;
import com.build.twitter_backend.repositories.UserRepository;
import com.build.twitter_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = service.getUsers();
            return ResponseEntity.ok(users);
        }

        catch (Exception error) {
            System.out.println("Internal server error");
            return ResponseEntity.internalServerError().build();
        }
    }
}
