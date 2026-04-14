package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.DTO.LikeDTO;
import com.dasaczzz.tempy.Exception.ConflictException;
import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.IdLike;
import com.dasaczzz.tempy.Model.LikeModel;
import com.dasaczzz.tempy.Model.PostModel;
import com.dasaczzz.tempy.Model.UserModel;
import com.dasaczzz.tempy.Repository.LikeRepository;
import com.dasaczzz.tempy.Repository.PostRepository;
import com.dasaczzz.tempy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {

  private final LikeRepository likeRepository;
  private final UserRepository userRepository;
  private final PostRepository postRepository;

  @Override
  public BaseResponse<LikeDTO> createRecord(LikeDTO record) {
    IdLike likeId = new IdLike(record.getIdPost(), record.getIdUser());
    if (likeRepository.existsById(likeId)) {
      throw new ConflictException("The user already liked this post");
    }

    PostModel post = postRepository
        .findById(record.getIdPost())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", record.getIdPost())));
    UserModel user = userRepository
        .findById(record.getIdUser())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", record.getIdUser())));

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
    LikeModel like = likeRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The like with id %s has not been found", id)));
    return BaseResponse.ok(mapToDTO(like));
  }

  @Override
  public BaseResponse<LikeDTO> deleteRecord(IdLike id) {
    return null;
  }

  private LikeDTO mapToDTO(LikeModel like) {
    return LikeDTO.builder().idPost(like.getPost().getId()).idUser(like.getUser().getId()).build();
  }

}
