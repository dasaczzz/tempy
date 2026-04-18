package com.dasaczzz.tempy.post.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class CreatePostDTO {

  @NotBlank(message = "the text is required")
  private String text;

  @NotNull(message = "the deadline is required")
  @Future(message = "The deadline must be a future date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime deadline;

  @NotNull(message = "the visibility is required")
  private Boolean isPublic;

  private Boolean isActive;

  @NotNull(message = "the id of the user is required")
  private UUID idUser;

}
