package com.dasaczzz.tempy.Controller;

import com.dasaczzz.tempy.Exception.ResourceNotFound;
import com.dasaczzz.tempy.Lib.BaseResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle when the request miss the body (error 400)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse<String>> handleEmptyBody(HttpMessageNotReadableException e) {

        // remove all boilerplate text of the error message
        String shortMessage = e.getMessage().split(":")[0];
        BaseResponse<String> response = BaseResponse.fail(shortMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // handle when the body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<String>> handleBadBody(MethodArgumentNotValidException e) {

        // get all the body validation errors and save it to display
        String errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        BaseResponse<String> response = BaseResponse.fail(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<BaseResponse<String>> handleResourceNotFound(ResourceNotFound e) {
        BaseResponse<String> response = BaseResponse.fail(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
