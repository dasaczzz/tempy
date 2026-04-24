package com.dasaczzz.tempy.post;

import java.util.UUID;
import com.dasaczzz.tempy.common.BaseService;
import com.dasaczzz.tempy.lib.BaseResponse;
import com.dasaczzz.tempy.post.dtos.CreatePostDTO;
import com.dasaczzz.tempy.post.dtos.ResponsePostDTO;
import com.dasaczzz.tempy.post.dtos.UpdatePostDTO;

public interface PostService extends BaseService<UUID, CreatePostDTO, ResponsePostDTO> {
  BaseResponse<ResponsePostDTO> updateRecord(UUID id, UpdatePostDTO record);
}
