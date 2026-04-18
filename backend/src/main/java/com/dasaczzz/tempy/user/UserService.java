package com.dasaczzz.tempy.user;

import java.util.UUID;
import com.dasaczzz.tempy.common.BaseService;
import com.dasaczzz.tempy.user.dtos.CreateUserDTO;
import com.dasaczzz.tempy.user.dtos.ResponseUserDTO;

public interface UserService extends BaseService<UUID, CreateUserDTO, ResponseUserDTO> { }
