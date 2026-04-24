package com.dasaczzz.tempy.post.dtos;

import java.time.Instant;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

public record UpdatePostDTO(
    @Size(max = 255, message = "the text is too long")
    String text,
    @Future(message = "The deadline must be a future date")
    Instant deadline,
    Boolean isPublic,
    Boolean isActive
) {

  public boolean isEmpty() { return text == null && deadline == null && isPublic == null && isActive == null; }

}
