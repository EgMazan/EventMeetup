package com.egorsproject.eventmeetup.dto;

import java.time.Instant;

public record UserAdminDto(
        Long id,
        String email,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
