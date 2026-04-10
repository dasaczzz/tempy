package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.UserModel;
import com.dasaczzz.tempy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;

  @Override
  public BaseResponse<UserModel> createRecord(UserModel record) {
    UserModel user = userRepository.save(record);
    return BaseResponse.ok(user);
  }

  @Override
  public BaseResponse<List<UserModel>> getRecords() {
    List<UserModel> users = userRepository.findAll();
    return BaseResponse.ok(users);
  }

  @Override
  public BaseResponse<UserModel> getRecordById(String id) {
    UserModel user = userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The user with id %s has not been found", id)));
    return BaseResponse.ok(user);
  }

  @Override
  public BaseResponse<UserModel> deleteRecord(String id) {
    return null;
  }

}
