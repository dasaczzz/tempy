package com.dasaczzz.tempy.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateUserDTO(
    @NotBlank(message = "the username is required")
    @Size(max = 50, message = "the username is too long")
    String username,
    @NotBlank(message = "the email is required")
    @Size(max=70, message = "the email is too long")
    @Email(message = "invalid email format")
    String email,
    @NotBlank(message = "the password is required")
    @Size(min = 6, message = "password out of limits", max = 70)
    String password,
    @URL(message = "invalid link format")
    @Size(max=2048, message = "the link is too long")
    String profilePicture
) { }
