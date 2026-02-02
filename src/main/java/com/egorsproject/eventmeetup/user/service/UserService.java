package com.egorsproject.eventmeetup.user.service;

import com.egorsproject.eventmeetup.user.domain.User;
import com.egorsproject.eventmeetup.user.dto.CreateUserRequest;
import com.egorsproject.eventmeetup.user.dto.UpdateUserRequest;
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
    public UserDto createUser(CreateUserRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException (
                    "User with email already exists: " + request.getEmail()
            );
        }
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("User with id: "+id+" not found");
        }
        userRepository.deleteById(id);
    }
    public UserDto updateUser(Long id, UpdateUserRequest request){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User with id " + id +" not found"));
        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User with email already exists"+ request.getEmail());
        }
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        User updateUser = userRepository.save(user);
        return userMapper.toDto(updateUser);
    }
    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User with id "+id+" not found"));
        return userMapper.toDto(user);
    }
}
