package com.egorsproject.eventmeetup.user.service;

import com.egorsproject.eventmeetup.user.domain.User;
import com.egorsproject.eventmeetup.user.dto.UserDto;
import com.egorsproject.eventmeetup.user.mapper.UserMapper;
import com.egorsproject.eventmeetup.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<UserDto> getAllUsers(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent().stream().map(userMapper::toDto).toList();
    }
}
