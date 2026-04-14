package com.dasaczzz.tempy.DTO;

import com.dasaczzz.tempy.Model.Enum.TypeContent;
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
