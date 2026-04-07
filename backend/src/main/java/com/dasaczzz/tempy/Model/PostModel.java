package com.dasaczzz.tempy.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPost;

    @NotBlank(message = "the text is required")
    @Column(nullable = false)
    private String text;

    @NotNull(message = "the deadline is required")
    @CreationTimestamp
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @NotNull(message = "the visibility is required")
    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = false)
    private Boolean isActive = true;

    @NotNull(message = "the id of the user is required")
    @ManyToOne
    @JoinColumn(name="idUser", nullable = false)
    private UserModel iduser;
}
