package com.dasaczzz.tempy.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponsePostDTO {

  private UUID id;
  private String text;
  private LocalDateTime deadline;
  private boolean isPublic;
  private boolean isActive;
  private UUID idUser;
  private String username;
  private String profilePicture;

}
