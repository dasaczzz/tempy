package com.dasaczzz.tempy.common;

import com.dasaczzz.tempy.Lib.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

// ID: id type of the model. C: CreateDTO, R: ResponseDTO
public interface BaseController<ID, C, R> {

  ResponseEntity<BaseResponse<R>> createRecord(C record);

  ResponseEntity<BaseResponse<List<R>>> getRecords();

  ResponseEntity<BaseResponse<R>> getRecordById(ID id);

  ResponseEntity<BaseResponse<R>> deleteRecord(ID id);

}
