package com.egorsproject.eventmeetup.user.controller;

import com.egorsproject.eventmeetup.user.dto.CreateUserRequest;
import com.egorsproject.eventmeetup.user.dto.UpdateUserRequest;
import com.egorsproject.eventmeetup.user.dto.UserDto;
import com.egorsproject.eventmeetup.user.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir){
        log.info("Get /api/users was called with page = {} and size ={}",page,size);
        List<UserDto> users = userService.getAllUsers(page, size, sortBy, sortDir);
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @PostMapping
    public ResponseEntity<UserDto> create(
            @Valid @RequestBody CreateUserRequest request){
        log.info("Post /api/users was called");
        UserDto createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        log.info("Delete /api/users/{} called", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request){
        log.info("Put /api/users/{} called" , id);
        UserDto updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }
}
