package com.dasaczzz.tempy.follow;

import com.dasaczzz.tempy.user.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Follow")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowModel {
  @EmbeddedId
  private IdFollow id;

  @ManyToOne()
  @JoinColumn(name = "idFollowed", referencedColumnName = "id", insertable = false, updatable = false)
  private UserModel followed;

  @ManyToOne()
  @JoinColumn(name = "idFollower", referencedColumnName = "id", insertable = false, updatable = false)
  private UserModel follower;

  public FollowModel(UserModel followed, UserModel follower) {
    this.followed = followed;
    this.follower = follower;
    this.id = new IdFollow(followed.getId(), follower.getId());
  }
}
