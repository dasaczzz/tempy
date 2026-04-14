package com.dasaczzz.tempy.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

  @NotBlank(message = "the username is required")
  @Size(max = 50)
  private String username;

  @NotBlank(message = "the email is required")
  @Email(message = "invalid email format")
  private String email;

  @NotBlank(message = "the password is required")
  @Size(min = 6, message = "minimum 6 characters")
  private String password;
  private String profilePicture;

}
