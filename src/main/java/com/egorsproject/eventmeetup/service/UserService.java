package com.egorsproject.eventmeetup.service;

import com.egorsproject.eventmeetup.dto.UserDto;
import com.egorsproject.eventmeetup.mapper.UserMapper;
import com.egorsproject.eventmeetup.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
}
