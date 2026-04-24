package com.dasaczzz.tempy.content.dtos;

import java.util.UUID;
import com.dasaczzz.tempy.content.TypeContent;

public record ResponseContentDTO(
    UUID id,
    String link,
    TypeContent type,
    UUID idPost
) { }
