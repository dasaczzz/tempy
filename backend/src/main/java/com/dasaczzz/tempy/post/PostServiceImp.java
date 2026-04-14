package com.dasaczzz.tempy.post;

import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;
import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.user.UserModel;
import com.dasaczzz.tempy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  public BaseResponse<ResponsePostDTO> createRecord(CreatePostDTO record) {
    UserModel user = userRepository
        .findById(record.getIdUser())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", record.getIdUser())));
    PostModel post = PostModel
        .builder()
        .text(record.getText())
        .deadline(record.getDeadline())
        .isPublic(record.getIsPublic())
        .isActive(true)
        .user(user)
        .build();
    postRepository.save(post);
    return BaseResponse.ok(mapToDTO(post));
  }

  @Override
  public BaseResponse<List<ResponsePostDTO>> getRecords() {
    List<PostModel> posts = postRepository.findAll();
    List<ResponsePostDTO> postsDTO = posts.stream().map(this::mapToDTO).toList();
    return BaseResponse.ok(postsDTO);
  }

  @Override
  public BaseResponse<ResponsePostDTO> getRecordById(UUID id) {
    PostModel post = postRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", id)));
    return BaseResponse.ok(mapToDTO(post));
  }

  @Override
  public BaseResponse<ResponsePostDTO> deleteRecord(UUID id) { return null; }

  private ResponsePostDTO mapToDTO(PostModel post) {
    return ResponsePostDTO
        .builder()
        .id(post.getId())
        .text(post.getText())
        .deadline(post.getDeadline())
        .isPublic(post.getIsPublic())
        .isActive(post.getIsActive())
        .idUser(post.getUser().getId())
        .username(post.getUser().getUsername())
        .profilePicture(post.getUser().getProfilePicture())
        .build();
  }

}
