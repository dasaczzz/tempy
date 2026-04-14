package com.dasaczzz.tempy.user;

import com.dasaczzz.tempy.common.BaseService;
import com.dasaczzz.tempy.user.dtos.CreateUserDTO;
import com.dasaczzz.tempy.user.dtos.ResponseUserDTO;

import java.util.UUID;

public interface UserService extends BaseService<UUID, CreateUserDTO, ResponseUserDTO> { }
