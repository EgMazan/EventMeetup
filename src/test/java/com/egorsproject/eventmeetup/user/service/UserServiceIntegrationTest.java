package com.egorsproject.eventmeetup.user.service;

import com.egorsproject.eventmeetup.IntegrationTestBase;
import com.egorsproject.eventmeetup.user.domain.User;
import com.egorsproject.eventmeetup.user.dto.CreateUserRequest;
import com.egorsproject.eventmeetup.user.dto.UserDto;
import com.egorsproject.eventmeetup.user.mapper.UserMapper;
import com.egorsproject.eventmeetup.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class UserServiceIntegrationTest extends IntegrationTestBase {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private CreateUserRequest testUserRequest;
    @BeforeEach
    void setup(){
        testUserRequest=new CreateUserRequest("Test User","testuser@mail.com");
    }
    @Test
    void createUser_ShouldSaveUser(){
        var createdUser = userService.createUser(testUserRequest);
        assertNotNull(createdUser.id());
        assertEquals("Test User",createdUser.name());
        assertEquals("testuser@mail.com",createdUser.email());
        User userInDb = userRepository.findById(createdUser.id()).orElseThrow();
        assertEquals(createdUser.email(),userInDb.getEmail());
    }
    @Test
    void deleteUser_ShouldRemoveUser(){
        UserDto createdUser = userService.createUser(testUserRequest);
        List<UserDto> allUsersBefore = userService.getAllUsers(0, 10, "email", "ASC");
        assertTrue(allUsersBefore.stream().anyMatch(u->u.id().equals(createdUser.id())));
        userService.deleteUser(createdUser.id());
        List<UserDto> allUsersAfter = userService.getAllUsers(0, 10, "email", "ASC");
        assertFalse(allUsersAfter.stream().anyMatch(u->u.id().equals(createdUser.id())));
    }
    @Test
    void getAllUsers_ShouldReturnAllUsers(){
        UserDto user1 = userService.createUser(new CreateUserRequest("User1","user1@mail.com"));
        UserDto user2 = userService.createUser(new CreateUserRequest("User2","user2@mail.com"));
        List<UserDto> allUsers = userService.getAllUsers(0, 10, "email", "ASC");
        assertNotNull(allUsers);
        assertTrue(allUsers.size()>=2);
        assertTrue(allUsers.stream().anyMatch(u -> u.email().equals("user1@mail.com")));
        assertTrue(allUsers.stream().anyMatch(u -> u.email().equals("user2@mail.com")));
    }
}
