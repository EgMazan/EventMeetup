package com.egorsproject.eventmeetup.user.mapper;

import com.egorsproject.eventmeetup.user.domain.User;
import com.egorsproject.eventmeetup.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto (User user){
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
