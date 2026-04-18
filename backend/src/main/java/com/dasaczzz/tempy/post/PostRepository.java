package com.dasaczzz.tempy.post;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostModel, UUID> { }
