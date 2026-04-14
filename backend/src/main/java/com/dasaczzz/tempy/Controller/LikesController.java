package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.DTO.LikeDTO;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.IdLike;
import com.dasaczzz.tempy.Service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikesController {

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

  public ResponseEntity<BaseResponse<LikeDTO>> deleteRecord(IdLike id) {
    return null;
  }

}
