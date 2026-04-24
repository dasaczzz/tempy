package com.dasaczzz.tempy.post;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.dasaczzz.tempy.exception.BadRequestException;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;
import com.dasaczzz.tempy.post.dtos.UpdatePostDTO;
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
    UserModel user = userRepository.findById(record.idUser())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", record.idUser())));
    PostModel post =
        PostModel.builder().text(record.text()).deadline(record.deadline()).isPublic(record.isPublic()).isActive(true).isDeleted(false).user(user)
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
    PostModel post = findPostById(id);
    return BaseResponse.ok(mapToDTO(post));
  }

  @Override
  public BaseResponse<String> deleteRecord(UUID id) {
    PostModel post = findPostById(id);
    post.setIsDeleted(true);
    postRepository.save(post);
    return BaseResponse.ok("The post has been deleted successfully");
  }

  @Override
  public BaseResponse<ResponsePostDTO> updateRecord(UUID id, UpdatePostDTO record) {
    if (record.isEmpty()) { throw new BadRequestException("Request body is empty"); }
    PostModel post = findPostById(id);

    if (record.text() != null) { post.setText(record.text()); }
    if (record.deadline() != null) { post.setDeadline(record.deadline()); }
    if (record.isPublic() != null) { post.setIsPublic(record.isPublic()); }
    if (record.isActive() != null) { post.setIsActive(record.isActive()); }

    PostModel postUpdated = postRepository.save(post);
    return BaseResponse.ok(mapToDTO(postUpdated));
  }

  private ResponsePostDTO mapToDTO(PostModel post) {
    return new ResponsePostDTO(post.getId(), post.getText(), post.getDeadline(), post.getIsPublic(), post.getIsActive(), post.getIsDeleted(),
        post.getUser().getId(), post.getUser().getUsername(), post.getUser().getProfilePicture(), post.getCreatedAt());
  }

  private PostModel findPostById(UUID id) {
    return postRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", id)));
  }

}
