package com.dasaczzz.tempy.content.dtos;

import com.dasaczzz.tempy.content.TypeContent;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseContentDTO {

  private UUID id;
  private String link;
  private TypeContent type;
  private UUID idPost;

}
