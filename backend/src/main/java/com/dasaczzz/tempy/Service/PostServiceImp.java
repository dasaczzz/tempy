package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.DTO.PostDTO;
import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.PostModel;
import com.dasaczzz.tempy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

  private final PostRepository postRepository;

  @Override
  public BaseResponse<PostDTO> createRecord(PostModel record) {
    PostModel post = postRepository.save(record);
    PostModel fullPost = postRepository
        .findById(post.getIdPost())
        .orElseThrow(() -> new ResourceNotFound("error finding the saved post"));

    return BaseResponse.ok(mapToDTO(fullPost));
  }

  @Override
  public BaseResponse<List<PostDTO>> getRecords() {
    List<PostModel> posts = postRepository.findAll();
    List<PostDTO> postsDTO = posts.stream().map(this::mapToDTO).toList();
    return BaseResponse.ok(postsDTO);
  }

  @Override
  public BaseResponse<PostDTO> getRecordById(String id) {
    PostModel post = postRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The post with id %s has not been found", id)));
    return BaseResponse.ok(mapToDTO(post));
  }

  @Override
  public BaseResponse<PostDTO> deleteRecord(String id) {
    return null;
  }

  private PostDTO mapToDTO(PostModel post) {
    return PostDTO
        .builder()
        .idPost(post.getIdPost())
        .text(post.getText())
        .deadline(post.getDeadline())
        .isPublic(post.getIsPublic())
        .isActive(post.getIsActive())
        .idUser(post.getIdUser().getIdUser())
        .username(post.getIdUser().getUsername())
        .profilePicture(post.getIdUser().getProfilePicture())
        .build();
  }

}
