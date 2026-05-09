package com.dasaczzz.tempy.post.dtos;

import java.time.Instant;
import java.util.UUID;

public record ResponsePostDTO(
    UUID id,
    String text,
    Instant deadline,
    Boolean isPublic,
    Boolean isDeleted,
    UUID idUser,
    String username,
    String profilePicture,
    Instant createdAt
) { }
