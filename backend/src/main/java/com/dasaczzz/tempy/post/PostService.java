package com.dasaczzz.tempy.post;

import java.util.UUID;
import com.dasaczzz.tempy.common.BaseService;
import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;

public interface PostService extends BaseService<UUID, CreatePostDTO, ResponsePostDTO> { }
