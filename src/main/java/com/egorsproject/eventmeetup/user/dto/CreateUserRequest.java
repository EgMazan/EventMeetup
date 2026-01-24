package com.egorsproject.eventmeetup.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}