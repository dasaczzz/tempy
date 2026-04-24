package com.dasaczzz.tempy.common;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.dasaczzz.tempy.lib.BaseResponse;

// ID: id type of the model. C: CreateDTO, R: ResponseDTO
public interface BaseController<ID, C, R> {

  ResponseEntity<BaseResponse<R>> createRecord(C record);

  ResponseEntity<BaseResponse<List<R>>> getRecords();

  ResponseEntity<BaseResponse<R>> getRecordById(ID id);

  ResponseEntity<BaseResponse<String>> deleteRecord(ID id);

}
