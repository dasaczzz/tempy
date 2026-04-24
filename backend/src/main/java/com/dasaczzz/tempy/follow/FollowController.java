package com.dasaczzz.tempy.follow;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dasaczzz.tempy.follow.dtos.FollowDTO;
import com.dasaczzz.tempy.lib.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  @PostMapping("/")
  public ResponseEntity<BaseResponse<FollowDTO>> createRecord(@Valid @RequestBody FollowDTO follow) {
    return new ResponseEntity<>(followService.createRecord(follow), HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<BaseResponse<List<FollowDTO>>> getRecords() {
    return new ResponseEntity<>(followService.getRecords(), HttpStatus.OK);
  }

  @GetMapping("/{idFollowed}/{idFollower}")
  public ResponseEntity<BaseResponse<FollowDTO>> getRecordById(@PathVariable UUID idFollowed, @PathVariable UUID idFollower) {
    return new ResponseEntity<>(followService.getRecordById(new IdFollow(idFollowed, idFollower)), HttpStatus.OK);
  }

  @DeleteMapping("/{idFollowed}/{idFollower}")
  public ResponseEntity<BaseResponse<String>> deleteRecord(@PathVariable UUID idFollowed, @PathVariable UUID idFollower) {
    return new ResponseEntity<>(followService.deleteRecord(new IdFollow(idFollowed, idFollower)), HttpStatus.OK);
  }

}
