package com.dasaczzz.tempy.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "`Like`")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(IdLike.class)
public class LikeModel extends BaseModel {

  @NotNull(message = "the id of the post is required")
  @Id
  @ManyToOne
  @JoinColumn(name = "idPost", nullable = false)
  private PostModel idPost;

  @NotNull(message = "the id of the user is required")
  @Id
  @ManyToOne
  @JoinColumn(name = "idUser", nullable = false)
  private UserModel idUser;

}
