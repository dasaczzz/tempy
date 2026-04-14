package com.dasaczzz.tempy.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdLike implements Serializable {

  @Column(name = "idPost", nullable = false)
  private UUID idPost;

  @Column(name = "idUser", nullable = false)
  private UUID idUser;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!( o instanceof IdLike that ))
      return false;
    return Objects.equals(idUser, that.idUser) && Objects.equals(idPost, that.idPost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUser, idPost);
  }

}