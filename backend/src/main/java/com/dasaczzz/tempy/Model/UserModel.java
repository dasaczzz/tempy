package com.dasaczzz.tempy.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseModel {

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false, length = 50)
  private String email;

  @Column(nullable = false, length = 70)
  private String password;

  @Column
  private String profilePicture;

}
