package com.dasaczzz.tempy.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
