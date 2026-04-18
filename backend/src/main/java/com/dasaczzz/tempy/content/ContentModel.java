package com.dasaczzz.tempy.content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.dasaczzz.tempy.common.BaseModel;
import com.dasaczzz.tempy.post.PostModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Content")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentModel extends BaseModel {

  @Column(nullable = false)
  private String link;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TypeContent type;

  @ManyToOne
  @JoinColumn(name = "idPost", nullable = false)
  private PostModel idPost;

}
