package com.dasaczzz.tempy.like;

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
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.like.dtos.LikeDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

  private final LikeService likeService;

  @PostMapping("/")
  public ResponseEntity<BaseResponse<LikeDTO>> createRecord(@Valid @RequestBody LikeDTO like) {
    return new ResponseEntity<>(likeService.createRecord(like), HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<BaseResponse<List<LikeDTO>>> getRecords() {
    return new ResponseEntity<>(likeService.getRecords(), HttpStatus.OK);
  }

  @GetMapping("/{idUser}/{idPost}")
  public ResponseEntity<BaseResponse<LikeDTO>> getRecordById(@PathVariable UUID idUser, @PathVariable UUID idPost) {
    return ResponseEntity.ok(likeService.getRecordById(new IdLike(idUser, idPost)));
  }

  public ResponseEntity<BaseResponse<LikeDTO>> deleteRecord() {
    return null;
  }

}
