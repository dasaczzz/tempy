package com.dasaczzz.tempy.user.dtos;

import java.time.Instant;
import java.util.UUID;

public record ResponseUserDTO(
    UUID id,
    String username,
    String email,
    String profilePicture,
    Instant createdAt
) { }
