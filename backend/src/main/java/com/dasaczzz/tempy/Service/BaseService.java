package com.dasaczzz.tempy.Service;

import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel, D> {

  BaseResponse<D> createRecord(T record);

  BaseResponse<List<D>> getRecords();

  BaseResponse<D> getRecordById(String id);

  BaseResponse<D> deleteRecord(String id);

}
