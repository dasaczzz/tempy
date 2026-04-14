package com.dasaczzz.tempy.Repository;

import com.dasaczzz.tempy.Model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> { }
