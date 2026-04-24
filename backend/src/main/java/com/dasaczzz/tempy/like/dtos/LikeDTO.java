package com.dasaczzz.tempy.like.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public record LikeDTO(@NotNull(message = "the id of the post is required") UUID idPost,
                      @NotNull(message = "the id of the user is required") UUID idUser) { }
