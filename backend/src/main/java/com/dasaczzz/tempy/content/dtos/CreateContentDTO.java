package com.dasaczzz.tempy.content.dtos;

import com.dasaczzz.tempy.content.TypeContent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

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
