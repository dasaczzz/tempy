package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.ContentModel;
import com.dasaczzz.tempy.Repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImp implements ContentService {

  private final ContentRepository contentRepository;

  @Override
  public BaseResponse<ContentModel> createRecord(ContentModel record) {
    ContentModel content = contentRepository.save(record);
    return BaseResponse.ok(content);
  }

  @Override
  public BaseResponse<List<ContentModel>> getRecords() {
    List<ContentModel> contents = contentRepository.findAll();
    return BaseResponse.ok(contents);
  }

  @Override
  public BaseResponse<ContentModel> getRecordById(String id) {
    ContentModel content = contentRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFound(String.format("The content with id %s has not been found", id)));
    return BaseResponse.ok(content);
  }

  @Override
  public BaseResponse<ContentModel> deleteRecord(String id) {
    return null;
  }

}
