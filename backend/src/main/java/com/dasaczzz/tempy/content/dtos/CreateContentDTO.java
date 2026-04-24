package com.dasaczzz.tempy.content.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.dasaczzz.tempy.content.TypeContent;

public record CreateContentDTO(@NotBlank(message = "the link is required") String link, @NotNull(message = "the type is required") TypeContent type,
                               @NotNull(message = "the id of the post is required") UUID idPost) { }
