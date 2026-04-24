package com.dasaczzz.tempy.user.dtos;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UpdateUserDTO(
    @Size(max = 50, message = "the username is too long")
    String username,
    @Size(min = 6, message = "password out of limits", max = 70)
    String password,
    @URL(message = "invalid link format")
    @Size(max = 2048, message = "the link is too long")
    String profilePicture
) {

  public boolean isEmpty() {
    return username == null && password == null && profilePicture == null;
  }

}
