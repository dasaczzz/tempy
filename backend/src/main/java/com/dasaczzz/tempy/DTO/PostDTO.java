package com.dasaczzz.tempy.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostDTO {
    private String idPost;
    private String text;
    private LocalDateTime deadline;
    private boolean isPublic;
    private boolean isActive;
    private String idUser;
    private String username;
    private String profilePicture;
}
