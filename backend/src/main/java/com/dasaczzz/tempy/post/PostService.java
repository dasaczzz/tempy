package com.dasaczzz.tempy.post;

import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;
import com.dasaczzz.tempy.common.BaseService;

import java.util.UUID;

public interface PostService extends BaseService<UUID, CreatePostDTO, ResponsePostDTO> { }
