package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.ContentModel;
import com.dasaczzz.tempy.Service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController implements BaseController<ContentModel, ContentModel> {

  @Autowired
  ContentService contentService;

  @Override
  @PostMapping("/")
  public ResponseEntity<BaseResponse<ContentModel>> createRecord(@Valid @RequestBody ContentModel content) {
    return new ResponseEntity<>(contentService.createRecord(content), HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/")
  public ResponseEntity<BaseResponse<List<ContentModel>>> getRecords() {
    return new ResponseEntity<>(contentService.getRecords(), HttpStatus.OK);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<ContentModel>> getRecordById(@PathVariable String id) {
    return new ResponseEntity<>(contentService.getRecordById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BaseResponse<ContentModel>> deleteRecord(String id) {
    return null;
  }

}
