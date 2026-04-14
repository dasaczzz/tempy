package com.dasaczzz.tempy.user;

import com.dasaczzz.tempy.user.dtos.CreateUserDTO;
import com.dasaczzz.tempy.user.dtos.ResponseUserDTO;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.common.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements BaseController<UUID, CreateUserDTO, ResponseUserDTO> {

  private final UserService userService;

  @Override
  @PostMapping("/")
  public ResponseEntity<BaseResponse<ResponseUserDTO>> createRecord(@Valid @RequestBody CreateUserDTO user) {
    return new ResponseEntity<>(userService.createRecord(user), HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/")
  public ResponseEntity<BaseResponse<List<ResponseUserDTO>>> getRecords() {
    return new ResponseEntity<>(userService.getRecords(), HttpStatus.OK);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<ResponseUserDTO>> getRecordById(@PathVariable UUID id) {
    return new ResponseEntity<>(userService.getRecordById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BaseResponse<ResponseUserDTO>> deleteRecord(UUID id) {
    return null;
  }

}
