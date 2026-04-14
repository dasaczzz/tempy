package com.dasaczzz.tempy.Model;

import com.dasaczzz.tempy.Model.Enum.TypeContent;
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
