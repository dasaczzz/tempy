package com.dasaczzz.tempy.follow.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FollowDTO {

  @NotNull(message = "the id of the followed is required")
  private UUID idFollowed;

  @NotNull(message = "the id of the follower is required")
  private UUID idFollower;

}
