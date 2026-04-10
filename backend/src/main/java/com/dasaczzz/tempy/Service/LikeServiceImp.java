package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.IdLike;
import com.dasaczzz.tempy.Model.LikeModel;
import com.dasaczzz.tempy.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImp implements LikeService {

  @Autowired
  LikeRepository likesRepository;

  @Override
  public BaseResponse<LikeModel> createRecord(LikeModel record) {
    LikeModel likes = likesRepository.save(record);
    return BaseResponse.ok(likes);
  }

  @Override
  public BaseResponse<List<LikeModel>> getRecords() {
    List<LikeModel> likes = likesRepository.findAll();
    return BaseResponse.ok(likes);
  }

  public BaseResponse<LikeModel> getLike(String postId, String userId) {
    IdLike id = new IdLike(postId, userId);
    LikeModel like = likesRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound("Like not found for this post and user"));
    return BaseResponse.ok(like);
  }

  @Override
  public BaseResponse<LikeModel> getRecordById(String id) {
    throw new UnsupportedOperationException("Use getLike(postId, userId) instead");
  }

  @Override
  public BaseResponse<LikeModel> deleteRecord(String id) {
    return null;
  }

}
