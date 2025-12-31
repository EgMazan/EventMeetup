package Domain;

import java.time.LocalDateTime;

public record User(
        long id,
        String email,
        String username,
        LocalDateTime createAt,
        LocalDateTime updatedAt
) {
}
