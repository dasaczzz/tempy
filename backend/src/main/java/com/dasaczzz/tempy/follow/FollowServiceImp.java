package com.dasaczzz.tempy.follow;

import java.util.List;
import org.springframework.stereotype.Service;
import com.dasaczzz.tempy.exception.ConflictException;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.follow.dtos.FollowDTO;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.user.UserModel;
import com.dasaczzz.tempy.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowServiceImp implements FollowService {

  private final FollowRepository followRepository;

  private final UserRepository userRepository;

  @Override
  public BaseResponse<FollowDTO> createRecord(FollowDTO record) {
    IdFollow idFollow = new IdFollow(record.getIdFollowed(), record.getIdFollower());

    if (record.getIdFollower().equals(record.getIdFollowed())) {
      throw new ConflictException("The user cannot follow herself.");
    }
    if (followRepository.existsById(idFollow)) {
      throw new ConflictException("The user is already following this user.");
    }

    UserModel followed = userRepository
        .findById(record.getIdFollowed())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idFollowed '%s' has not been found", record.getIdFollowed())));
    UserModel follower = userRepository
        .findById(record.getIdFollower())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idFollowed '%s' has not been found", record.getIdFollower())));

    FollowModel follow = followRepository.save(new FollowModel(followed, follower));
    return BaseResponse.ok(mapToDTO(follow));
  }

  @Override
  public BaseResponse<List<FollowDTO>> getRecords() {
    List<FollowModel> follows = followRepository.findAll();
    List<FollowDTO> followsResponse = follows.stream().map(this::mapToDTO).toList();
    return BaseResponse.ok(followsResponse);
  }

  @Override
  public BaseResponse<FollowDTO> getRecordById(IdFollow id) {
    FollowModel follow = followRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The follow with id %s has not been found", id)));
    return BaseResponse.ok(mapToDTO(follow));
  }

  @Override
  public BaseResponse<FollowDTO> deleteRecord(IdFollow idFollower) {
    return null;
  }

  private FollowDTO mapToDTO(FollowModel follow) {
    return FollowDTO.builder().idFollowed(follow.getFollowed().getId()).idFollower(follow.getFollower().getId()).build();
  }

}
