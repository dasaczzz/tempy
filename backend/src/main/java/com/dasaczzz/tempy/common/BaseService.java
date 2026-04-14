package com.dasaczzz.tempy.common;

import com.dasaczzz.tempy.Lib.BaseResponse;

import java.util.List;

// ID: id type of the model. C: CreateDTO, R: ResponseDTO
public interface BaseService<ID, C, R> {

  BaseResponse<R> createRecord(C record);

  BaseResponse<List<R>> getRecords();

  BaseResponse<R> getRecordById(ID id);

  BaseResponse<R> deleteRecord(ID id);

}
