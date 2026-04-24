package com.dasaczzz.tempy.content;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.dasaczzz.tempy.content.dtos.CreateContentDTO;
import com.dasaczzz.tempy.content.dtos.ResponseContentDTO;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.post.PostModel;
import com.dasaczzz.tempy.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentServiceImp implements ContentService {

  private final ContentRepository contentRepository;

  private final PostRepository postRepository;

  @Override
  public BaseResponse<ResponseContentDTO> createRecord(CreateContentDTO record) {
    PostModel post = postRepository.findById(record.idPost())
                                   .orElseThrow(() -> new ResourceNotFound(String.format("The idPost '%s' has not been found", record.idPost())));
    ContentModel content = ContentModel.builder().link(record.link()).type(record.type()).idPost(post).build();
    contentRepository.save(content);
    return BaseResponse.ok(mapToDTO(content));
  }

  @Override
  public BaseResponse<List<ResponseContentDTO>> getRecords() {
    List<ContentModel> contents = contentRepository.findAll();
    List<ResponseContentDTO> contentsDTO = contents.stream().map(this::mapToDTO).toList();
    return BaseResponse.ok(contentsDTO);
  }

  @Override
  public BaseResponse<ResponseContentDTO> getRecordById(UUID id) {
    ContentModel content =
        contentRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The content with id %s has not been found", id)));
    return BaseResponse.ok(mapToDTO(content));
  }

  @Override
  public BaseResponse<String> deleteRecord(UUID id) {
    ContentModel content =
        contentRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The content with id %s has not been found", id)));
    contentRepository.deleteById(content.getId());
    return BaseResponse.ok("The content has been deleted successfully");
  }

  private ResponseContentDTO mapToDTO(ContentModel content) {
    return new ResponseContentDTO(content.getId(), content.getLink(), content.getType(), content.getIdPost().getId());
  }

}
