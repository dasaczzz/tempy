package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.LikeModel;

public interface LikeService extends BaseService<LikeModel, LikeModel> {

  BaseResponse<LikeModel> getLike(String postId, String userId);

}
