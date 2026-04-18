package com.dasaczzz.tempy.follow;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdFollow implements Serializable {

  @Column(name = "idFollowed", nullable = false)
  private UUID idFollowed;

  @Column(name = "idFollower", nullable = false)
  private UUID idFollower;

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!( o instanceof IdFollow that )) { return false; }
    return Objects.equals(idFollowed, that.idFollowed) && Objects.equals(idFollower, that.idFollower);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idFollowed, idFollower);
  }

}
