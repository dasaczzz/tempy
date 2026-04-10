package com.dasaczzz.tempy.Model;

import com.dasaczzz.tempy.Model.Enum.TypeContent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Content")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentModel extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String idContent;

  @NotBlank(message = "the link is required")
  @Column(nullable = false)
  private String link;

  @NotNull(message = "the type is required")
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TypeContent type;

  @NotNull(message = "the id of the post is required")
  @ManyToOne
  @JoinColumn(name = "idPost", nullable = false)
  private PostModel idPost;

}
