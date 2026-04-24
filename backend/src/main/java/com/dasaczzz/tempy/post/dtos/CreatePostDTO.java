package com.dasaczzz.tempy.post.dtos;

import java.time.Instant;
import java.util.UUID;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostDTO(
    @NotBlank(message = "the text is required")
    @Size(max = 255, message = "the text is too long")
    String text,
    @NotNull(message = "the deadline is required")
    @Future(message = "The deadline must be a future date")
    Instant deadline,
    @NotNull(message = "the visibility is required")
    Boolean isPublic,
    Boolean isActive,
    @NotNull(message = "the id of the user is required")
    UUID idUser
) { }
