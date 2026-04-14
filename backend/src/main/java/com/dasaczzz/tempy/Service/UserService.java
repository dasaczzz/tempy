package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.DTO.CreateUserDTO;
import com.dasaczzz.tempy.DTO.ResponseUserDTO;

import java.util.UUID;

public interface UserService extends BaseService<UUID, CreateUserDTO, ResponseUserDTO> { }
