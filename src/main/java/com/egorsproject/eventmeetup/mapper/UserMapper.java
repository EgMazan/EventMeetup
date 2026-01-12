package com.egorsproject.eventmeetup.mapper;

import com.egorsproject.eventmeetup.domain.User;
import com.egorsproject.eventmeetup.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto (User user){
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
