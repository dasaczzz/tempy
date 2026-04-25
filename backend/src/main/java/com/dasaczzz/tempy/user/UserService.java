package com.dasaczzz.tempy.user;

import java.util.UUID;
import com.dasaczzz.tempy.common.BaseService;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.user.dtos.CreateUserDTO;
import com.dasaczzz.tempy.user.dtos.ResponseUserDTO;
import com.dasaczzz.tempy.user.dtos.UpdateUserDTO;

public interface UserService extends BaseService<UUID, CreateUserDTO, ResponseUserDTO> {
  BaseResponse<ResponseUserDTO> updateRecord(UUID id, UpdateUserDTO record);
}
