package com.dasaczzz.tempy.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.dasaczzz.tempy.post.PostModel;
import com.dasaczzz.tempy.user.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @ManyToOne
  @JoinColumn(name = "idPost", referencedColumnName = "id", insertable = false, updatable = false)
  private PostModel post;

  @ManyToOne
  @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
  private UserModel user;

  public LikeModel(PostModel post, UserModel user) {
    this.user = user;
    this.post = post;
    this.id = new IdLike(post.getId(), user.getId());
  }

}
