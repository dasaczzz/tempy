package com.dasaczzz.tempy.user;

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
import com.dasaczzz.tempy.common.BaseController;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.user.dtos.CreateUserDTO;
import com.dasaczzz.tempy.user.dtos.ResponseUserDTO;

import lombok.RequiredArgsConstructor;

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
  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<String>> deleteRecord(@PathVariable UUID id) {
    return new ResponseEntity<>(userService.deleteRecord(id), HttpStatus.OK);
  }

}
