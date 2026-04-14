package com.dasaczzz.tempy.follow;

import com.dasaczzz.tempy.follow.dtos.FollowDTO;
import com.dasaczzz.tempy.lib.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
}
