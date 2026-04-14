package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.DTO.CreatePostDTO;
import com.dasaczzz.tempy.DTO.ResponsePostDTO;

import java.util.UUID;

public interface PostService extends BaseService<UUID, CreatePostDTO, ResponsePostDTO> { }
