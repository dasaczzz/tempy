package com.dasaczzz.tempy.content.dtos;

import java.util.UUID;
import com.dasaczzz.tempy.content.TypeContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
