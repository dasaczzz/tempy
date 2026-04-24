package com.dasaczzz.tempy.user.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseUserDTO(UUID id, String username, String email, String profilePicture, LocalDateTime createdAt) { }
