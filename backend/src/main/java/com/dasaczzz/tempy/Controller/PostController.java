package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.DTO.CreatePostDTO;
import com.dasaczzz.tempy.DTO.ResponsePostDTO;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
