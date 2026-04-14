package com.dasaczzz.tempy.like.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LikeDTO {

  @NotNull(message = "the id of the post is required")
  private UUID idPost;

  @NotNull(message = "the id of the user is required")
  private UUID idUser;

}
