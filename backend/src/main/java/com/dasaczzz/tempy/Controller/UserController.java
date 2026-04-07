package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.Lib.BaseResponse;
import com.dasaczzz.tempy.Model.UserModel;
import com.dasaczzz.tempy.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements BaseController<UserModel, UserModel> {
    @Autowired UserService userService;

    @Override
    @PostMapping("/")
    public ResponseEntity<BaseResponse<UserModel>> createRecord(@Valid @RequestBody UserModel user) {
        return new ResponseEntity<>(userService.createRecord(user), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<UserModel>>> getRecords() {
        return new ResponseEntity<>(userService.getRecords(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserModel>> getRecordById(@PathVariable String id) {
        return new ResponseEntity<>(userService.getRecordById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<UserModel>> deleteRecord(String id) {
        return null;
    }
}
