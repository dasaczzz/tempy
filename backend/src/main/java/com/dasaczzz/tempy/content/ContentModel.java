package com.dasaczzz.tempy.content;

import com.dasaczzz.tempy.post.PostModel;
import com.dasaczzz.tempy.common.BaseModel;
import jakarta.persistence.*;
import lombok.*;

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
