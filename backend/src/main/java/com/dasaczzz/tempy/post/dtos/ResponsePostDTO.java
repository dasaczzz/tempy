package com.dasaczzz.tempy.post.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponsePostDTO(UUID id, String text, LocalDateTime deadline, boolean isPublic, boolean isActive, boolean isDeleted, UUID idUser,
                              String username, String profilePicture) { }
