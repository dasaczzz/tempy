package com.dasaczzz.tempy.content.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.dasaczzz.tempy.content.TypeContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContentDTO {

  @NotBlank(message = "the link is required")
  private String link;

  @NotNull(message = "the type is required")
  private TypeContent type;

  @NotNull(message = "the id of the post is required")
  private UUID idPost;

}
