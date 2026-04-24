package com.dasaczzz.tempy.follow.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public record FollowDTO(
    @NotNull(message = "the id of the followed is required")
    UUID idFollowed,
    @NotNull(message = "the id of the follower is required")
    UUID idFollower
) { }
