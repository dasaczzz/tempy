package com.dasaczzz.tempy.user;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.dasaczzz.tempy.exception.BadRequestException;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.user.dtos.CreateUserDTO;
import com.dasaczzz.tempy.user.dtos.ResponseUserDTO;
import com.dasaczzz.tempy.user.dtos.UpdateUserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private static final String DEFAULT_AVATAR = "https://cdn.tempy.com/avatars/default.webp";

  private final UserRepository userRepository;

  @Override
  public BaseResponse<ResponseUserDTO> createRecord(CreateUserDTO record) {
    UserModel user = UserModel.builder().username(record.username()).email(record.email()).password(record.password())
        .profilePicture(record.profilePicture() != null ? record.profilePicture() : DEFAULT_AVATAR).build();
    userRepository.save(user);
    userRepository.flush();
    return BaseResponse.ok(mapToDTO(user));
  }

  @Override
  public BaseResponse<List<ResponseUserDTO>> getRecords() {
    List<UserModel> users = userRepository.findAll();
    List<ResponseUserDTO> usersResponse = users.stream().map(this::mapToDTO).toList();
    return BaseResponse.ok(usersResponse);
  }

  @Override
  public BaseResponse<ResponseUserDTO> getRecordById(UUID id) {
    UserModel user = findUserById(id);
    return BaseResponse.ok(mapToDTO(user));
  }

  @Override
  public BaseResponse<String> deleteRecord(UUID id) {
    UserModel user = findUserById(id);
    userRepository.deleteById(user.getId());
    return BaseResponse.ok(String.format("The user '%s' has been deleted successfully", user.getUsername()));
  }

  @Override
  public BaseResponse<ResponseUserDTO> updateRecord(UUID id, UpdateUserDTO record) {
    if (record.isEmpty()) { throw new BadRequestException("Request body is empty"); }
    UserModel user = findUserById(id);

    if (record.username() != null) { user.setUsername(record.username()); }
    if (record.password() != null) { user.setPassword(record.password()); }
    if (record.profilePicture() != null) { user.setProfilePicture(record.profilePicture()); }

    UserModel userUpdated = userRepository.save(user);
    return BaseResponse.ok(mapToDTO(userUpdated));
  }

  private ResponseUserDTO mapToDTO(UserModel user) {
    return new ResponseUserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getProfilePicture(), user.getCreatedAt());
  }

  private UserModel findUserById(UUID id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", id)));
  }

}
