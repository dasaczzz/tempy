package com.dasaczzz.tempy.user.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class ResponseUserDTO {

  private UUID id;

  private String username;

  private String email;

  private String profilePicture;

  private LocalDateTime createdAt;

}
