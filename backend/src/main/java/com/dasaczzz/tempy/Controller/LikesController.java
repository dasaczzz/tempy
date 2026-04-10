package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.LikeModel;
import com.dasaczzz.tempy.Service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikesController implements BaseController<LikeModel, LikeModel> {

  private final LikeService likeService;

  @Override
  @PostMapping("/")
  public ResponseEntity<BaseResponse<LikeModel>> createRecord(@Valid @RequestBody LikeModel like) {
    return new ResponseEntity<>(likeService.createRecord(like), HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/")
  public ResponseEntity<BaseResponse<List<LikeModel>>> getRecords() {
    return new ResponseEntity<>(likeService.getRecords(), HttpStatus.OK);
  }

  @Override
  @Deprecated
  public ResponseEntity<BaseResponse<LikeModel>> getRecordById(@PathVariable String id) {
    throw new UnsupportedOperationException("to get a like by id's, please use this endpoint: /api/likes/{postId}/{userId}");
  }

  @GetMapping("/{postId}/{userId}")
  public ResponseEntity<BaseResponse<LikeModel>> getLike(@PathVariable String postId, @PathVariable String userId) {
    return new ResponseEntity<>(likeService.getLike(postId, userId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BaseResponse<LikeModel>> deleteRecord(String id) {
    return null;
  }

}
