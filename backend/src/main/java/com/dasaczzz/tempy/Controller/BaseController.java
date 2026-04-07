package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.BaseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController <T extends BaseModel, D> {
    ResponseEntity<BaseResponse<D>> createRecord(T record);
    ResponseEntity<BaseResponse<List<D>>> getRecords();
    ResponseEntity<BaseResponse<D>> getRecordById(String id);
    ResponseEntity<BaseResponse<D>> deleteRecord(String id);
}
