package com.dasaczzz.tempy.post;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.dasaczzz.tempy.common.BaseModel;
import com.dasaczzz.tempy.user.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostModel extends BaseModel {

  @Column(nullable = false)
  private String text;

  @Column(name = "deadline", nullable = false)
  private LocalDateTime deadline;

  @Column(nullable = false)
  private Boolean isPublic;

  @Column(nullable = false)
  private Boolean isActive;

  @ManyToOne()
  @JoinColumn(name = "idUser", nullable = false)
  private UserModel user;

}
