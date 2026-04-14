package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.DTO.CreateContentDTO;
import com.dasaczzz.tempy.DTO.ResponseContentDTO;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController implements BaseController<UUID, CreateContentDTO, ResponseContentDTO> {

  private final ContentService contentService;

  @Override
  @PostMapping("/")
  public ResponseEntity<BaseResponse<ResponseContentDTO>> createRecord(@Valid @RequestBody CreateContentDTO content) {
    return new ResponseEntity<>(contentService.createRecord(content), HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/")
  public ResponseEntity<BaseResponse<List<ResponseContentDTO>>> getRecords() {
    return new ResponseEntity<>(contentService.getRecords(), HttpStatus.OK);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<ResponseContentDTO>> getRecordById(@PathVariable UUID id) {
    return new ResponseEntity<>(contentService.getRecordById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BaseResponse<ResponseContentDTO>> deleteRecord(UUID id) {
    return null;
  }

}
