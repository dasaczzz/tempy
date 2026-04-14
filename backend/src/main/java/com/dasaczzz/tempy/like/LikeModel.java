package com.dasaczzz.tempy.like;

import com.dasaczzz.tempy.post.PostModel;
import com.dasaczzz.tempy.user.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`Like`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeModel {

  @EmbeddedId
  private IdLike id;

  @ManyToOne()
  @JoinColumn(name = "idPost", referencedColumnName = "id", insertable = false, updatable = false)
  private PostModel post;

  @ManyToOne()
  @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
  private UserModel user;

  public LikeModel(PostModel post, UserModel user) {
    this.user = user;
    this.post = post;
    this.id = new IdLike(post.getId(), user.getId());
  }

}
