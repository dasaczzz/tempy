package com.dasaczzz.tempy.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUser;

    @NotBlank(message = "the username is required")
    @Column(nullable = false)
    private String username;

    @NotBlank (message = "the email is required")
    @Email(message = "invalid email format")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "the password is required")
    @Column(nullable = false)
    private String password;

    @Column
    private String profilePicture = "https://resources.com/avatar.png";
}
