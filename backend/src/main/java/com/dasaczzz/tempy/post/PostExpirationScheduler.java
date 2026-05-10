package com.dasaczzz.tempy.post;

import java.time.Instant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostExpirationScheduler {

  private final PostRepository postRepository;

  @Scheduled(fixedRate = 60000)
  public void expirePosts() {
    postRepository.softDeleteExpiredPosts(Instant.now());
  }

}
