package com.dasaczzz.tempy.user.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {

  private UUID id;
  private String username;
  private String email;
  private String profilePicture;
  private LocalDateTime createdAt;

}
