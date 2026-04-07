package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.DTO.PostDTO;
import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.PostModel;
import com.dasaczzz.tempy.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController implements BaseController<PostModel, PostDTO>{

    @Autowired PostService postService;

    @PostMapping("/")
    @Override
    public ResponseEntity<BaseResponse<PostDTO>> createRecord(@Valid @RequestBody PostModel post) {
        return new ResponseEntity<>(postService.createRecord(post), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<BaseResponse<List<PostDTO>>> getRecords() {
        return new ResponseEntity<>(postService.getRecords(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<BaseResponse<PostDTO>> getRecordById(@PathVariable String id) {
        return new ResponseEntity<>(postService.getRecordById(id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<BaseResponse<PostDTO>> deleteRecord(String id) {
        return null;
    }
}
