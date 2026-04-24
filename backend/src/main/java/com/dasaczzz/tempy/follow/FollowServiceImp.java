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
    IdFollow idFollow = new IdFollow(record.idFollowed(), record.idFollower());

    if (record.idFollower().equals(record.idFollowed())) {
      throw new ConflictException("The user cannot follow herself.");
    }
    if (followRepository.existsById(idFollow)) {
      throw new ConflictException("The user is already following this user.");
    }

    UserModel followed = userRepository.findById(record.idFollowed())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idFollowed '%s' has not been found", record.idFollowed())));
    UserModel follower = userRepository.findById(record.idFollower())
        .orElseThrow(() -> new ResourceNotFound(String.format("The idFollowed '%s' has not been found", record.idFollower())));

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
    FollowModel follow =
        followRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The follow with id %s has not been found", id)));
    return BaseResponse.ok(mapToDTO(follow));
  }

  @Override
  public BaseResponse<String> deleteRecord(IdFollow id) {

    FollowModel follow =
        followRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The follow with id %s has not been found", id)));
    followRepository.deleteById(follow.getId());
    return BaseResponse.ok(
        String.format("The user '%s' stopped following '%s'", follow.getFollower().getUsername(), follow.getFollowed().getUsername()));
  }

  private FollowDTO mapToDTO(FollowModel follow) {
    return new FollowDTO(follow.getFollowed().getId(), follow.getFollower().getId());
  }

}
