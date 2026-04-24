package com.dasaczzz.tempy.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(@NotBlank(message = "the username is required") @Size(max = 50) String username,
                            @NotBlank(message = "the email is required") @Email(message = "invalid email format") String email,
                            @NotBlank(message = "the password is required") @Size(min = 6, message = "minimum 6 characters") String password,
                            String profilePicture) { }
