package com.dasaczzz.tempy.post;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;
import com.dasaczzz.tempy.user.UserModel;
import com.dasaczzz.tempy.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

  private final PostRepository postRepository;

  private final UserRepository userRepository;

  @Override
  public BaseResponse<ResponsePostDTO> createRecord(CreatePostDTO record) {
    UserModel user = userRepository.findById(record.getIdUser())
                                   .orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", record.getIdUser())));
    PostModel post =
        PostModel.builder().text(record.getText()).deadline(record.getDeadline()).isPublic(record.getIsPublic()).isActive(true).user(user).build();
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
    PostModel post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", id)));

    return BaseResponse.ok(mapToDTO(post));
  }

  @Override
  public BaseResponse<String> deleteRecord(UUID id) {
    PostModel post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", id)));
    post.setIsDeleted(true);
    postRepository.save(post);
    return BaseResponse.ok("The post has been deleted successfully");
  }

  private ResponsePostDTO mapToDTO(PostModel post) {
    return new ResponsePostDTO(post.getId(), post.getText(), post.getDeadline(), post.getIsPublic(), post.getIsActive(), post.getIsDeleted(),
        post.getUser().getId(), post.getUser().getUsername(), post.getUser().getProfilePicture());
  }

}
