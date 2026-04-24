package com.dasaczzz.tempy.content.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import com.dasaczzz.tempy.content.TypeContent;

public record CreateContentDTO(
    @NotBlank(message = "the link is required")
    @URL(message = "invalid link format")
    @Size(max=2048, message = "the link is too long")
    String link,
    @NotNull(message = "the type is required")
    TypeContent type,
    @NotNull(message = "the id of the post is required")
    UUID idPost
) { }
