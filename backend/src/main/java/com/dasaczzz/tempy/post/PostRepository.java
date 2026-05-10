package com.dasaczzz.tempy.post;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<PostModel, UUID> {

  @Query("""
      SELECT p FROM PostModel p
      WHERE p.user.id = :userId
      ORDER BY p.createdAt DESC
      """)
  List<PostModel> findPostsByUserId(@Param("userId") UUID userId);

  @Query("""
      SELECT p FROM PostModel p
      WHERE p.user.id IN (
          SELECT f.followed.id FROM FollowModel f WHERE f.follower.id = :userId
      )
      AND p.isDeleted = false
      AND p.isPublic = true
      ORDER BY p.createdAt DESC
      """)
  List<PostModel> findFeedByUserId(@Param("userId") UUID userId);

}
