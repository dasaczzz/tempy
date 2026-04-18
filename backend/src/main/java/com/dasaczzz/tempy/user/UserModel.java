package com.dasaczzz.tempy.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.dasaczzz.tempy.common.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
