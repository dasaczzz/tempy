package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.DTO.CreateUserDTO;
import com.dasaczzz.tempy.DTO.ResponseUserDTO;
import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.UserModel;
import com.dasaczzz.tempy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private static final String DEFAULT_AVATAR = "https://cdn.tempy.com/avatars/default.webp";
  private final UserRepository userRepository;

  @Override
  public BaseResponse<ResponseUserDTO> createRecord(CreateUserDTO record) {
    UserModel user = UserModel
        .builder()
        .username(record.getUsername())
        .email(record.getEmail())
        .password(record.getPassword())
        .profilePicture(record.getProfilePicture() != null ? record.getProfilePicture() : DEFAULT_AVATAR)
        .build();
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
    UserModel user = userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The idUser '%s' has not been found", id)));
    return BaseResponse.ok(mapToDTO(user));
  }

  @Override
  public BaseResponse<ResponseUserDTO> deleteRecord(UUID id) {
    return null;
  }

  private ResponseUserDTO mapToDTO(UserModel user) {
    return ResponseUserDTO
        .builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .profilePicture(user.getProfilePicture())
        .createdAt(user.getCreatedAt())
        .build();
  }

}
