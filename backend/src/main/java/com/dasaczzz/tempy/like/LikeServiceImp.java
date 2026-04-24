package com.dasaczzz.tempy.like;

import java.util.List;
import org.springframework.stereotype.Service;
import com.dasaczzz.tempy.exception.ConflictException;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.like.dtos.LikeDTO;
import com.dasaczzz.tempy.post.PostModel;
import com.dasaczzz.tempy.post.PostRepository;
import com.dasaczzz.tempy.user.UserModel;
import com.dasaczzz.tempy.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {

  private final LikeRepository likeRepository;

  private final UserRepository userRepository;

  private final PostRepository postRepository;

  @Override
  public BaseResponse<LikeDTO> createRecord(LikeDTO record) {
    IdLike idLike = new IdLike(record.idPost(), record.idUser());
    if (likeRepository.existsById(idLike)) {
      throw new ConflictException("The user already liked this post");
    }

    PostModel post = postRepository.findById(record.idPost())
                                   .orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", record.idPost())));
    UserModel user = userRepository.findById(record.idUser())
                                   .orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", record.idUser())));

    LikeModel like = likeRepository.save(new LikeModel(post, user));
    return BaseResponse.ok(mapToDTO(like));
  }

  @Override
  public BaseResponse<List<LikeDTO>> getRecords() {
    List<LikeModel> likes = likeRepository.findAll();
    List<LikeDTO> likesResponse = likes.stream().map(this::mapToDTO).toList();
    return BaseResponse.ok(likesResponse);
  }

  @Override
  public BaseResponse<LikeDTO> getRecordById(IdLike id) {
    LikeModel like = likeRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The like with id %s has not been found", id)));
    return BaseResponse.ok(mapToDTO(like));
  }

  @Override
  public BaseResponse<String> deleteRecord(IdLike id) {
    LikeModel like = likeRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The like with id %s has not been found", id)));
    likeRepository.deleteById(like.getId());
    return BaseResponse.ok("The like has been deleted successfully");
  }

  private LikeDTO mapToDTO(LikeModel like) {
    return new LikeDTO(like.getPost().getId(), like.getUser().getId());
  }

}
