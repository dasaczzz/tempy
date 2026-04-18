package com.dasaczzz.tempy.post;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dasaczzz.tempy.common.BaseController;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController implements BaseController<UUID, CreatePostDTO, ResponsePostDTO> {

  private final PostService postService;

  @PostMapping("/")
  @Override
  public ResponseEntity<BaseResponse<ResponsePostDTO>> createRecord(@Valid @RequestBody CreatePostDTO post) {
    BaseResponse<ResponsePostDTO> response = postService.createRecord(post);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/")
  @Override
  public ResponseEntity<BaseResponse<List<ResponsePostDTO>>> getRecords() {
    return new ResponseEntity<>(postService.getRecords(), HttpStatus.OK);

  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<BaseResponse<ResponsePostDTO>> getRecordById(@PathVariable UUID id) {
    return new ResponseEntity<>(postService.getRecordById(id), HttpStatus.OK);

  }

  @Override
  public ResponseEntity<BaseResponse<ResponsePostDTO>> deleteRecord(UUID id) {
    return null;
  }

}
