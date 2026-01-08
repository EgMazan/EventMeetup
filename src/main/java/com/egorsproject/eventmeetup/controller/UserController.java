package com.egorsproject.eventmeetup.controller;

import com.egorsproject.eventmeetup.domain.User;
import com.egorsproject.eventmeetup.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repository;
    public UserController(UserRepository repository){
        this.repository=repository;
    }
    @GetMapping
    public List<User> getAll(){
        return repository.findAll();
    }
}
