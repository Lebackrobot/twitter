package com.build.twitter_backend.services;

import com.build.twitter_backend.entities.User;
import com.build.twitter_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Long userId) {
         return repository.getReferenceById(userId);
    }
}
