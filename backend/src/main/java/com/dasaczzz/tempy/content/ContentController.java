package com.dasaczzz.tempy.content;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dasaczzz.tempy.common.BaseController;
import com.dasaczzz.tempy.content.dtos.CreateContentDTO;
import com.dasaczzz.tempy.content.dtos.ResponseContentDTO;
import com.dasaczzz.tempy.lib.BaseResponse;

import lombok.RequiredArgsConstructor;

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
  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<String>> deleteRecord(UUID id) {
    return new ResponseEntity<>(contentService.deleteRecord(id), HttpStatus.OK);
  }

}
