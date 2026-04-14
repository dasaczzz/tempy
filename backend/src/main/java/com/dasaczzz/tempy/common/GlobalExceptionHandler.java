package com.dasaczzz.tempy.common;

import com.dasaczzz.tempy.exception.ConflictException;
import com.dasaczzz.tempy.exception.ResourceNotFound;
import com.dasaczzz.tempy.lib.BaseResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tools.jackson.databind.exc.InvalidFormatException;
import tools.jackson.databind.exc.UnrecognizedPropertyException;

import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 400 — bad body in the request (invalid JSON, invalid UUID, unknown field)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseResponse<?>> handleMessageNotReadable(HttpMessageNotReadableException ex) {
    Throwable cause = ex.getCause();

    if (cause instanceof InvalidFormatException invalidFormat) {
      return handleInvalidFormat(invalidFormat);
    }
    if (cause instanceof UnrecognizedPropertyException unrecognized) {
      return handleUnrecognizedProperty(unrecognized);
    }
    if (isEmptyBody(ex)) {
      return handleEmptyBody();
    }

    return badRequest("Invalid request format");
  }

  // 400 — failed validations (@NotNull, @NotBlank, etc.)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseResponse<?>> handleValidation(MethodArgumentNotValidException ex) {
    String errors = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(", "));
    return badRequest(errors);
  }

  // 400 — invalid path param
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<BaseResponse<?>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    if (UUID.class.equals(ex.getRequiredType())) {
      return badRequest(String.format("The %s '%s' has not been found", ex.getName(), ex.getValue()));
    }
    return badRequest(String.format("Invalid value '%s' for parameter '%s'", ex.getValue(), ex.getName()));
  }

  // 404 — not found
  @ExceptionHandler(ResourceNotFound.class)
  public ResponseEntity<BaseResponse<?>> handleResourceNotFound(ResourceNotFound ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.fail(ex.getMessage()));
  }

  // 500 — generic error
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<?>> handleGeneric() {
    return ResponseEntity.internalServerError().body(BaseResponse.fail("Internal server error"));
  }

  // 409 — duplicated entries for unique fields
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<BaseResponse<?>> handleDataIntegrity(DataIntegrityViolationException ex) {
    String message = "Resource already exists";

    Throwable cause = ex.getMostSpecificCause();
    String causeMessage = cause.getMessage();

    if (causeMessage != null) {
      if (causeMessage.contains("username")) {
        message = "The username is already taken";
      } else if (causeMessage.contains("email")) {
        message = "The email is already registered";
      }
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponse.fail(message));
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<BaseResponse<?>> handleConflict(ConflictException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponse.fail(ex.getMessage()));
  }

  // private methods

  private ResponseEntity<BaseResponse<?>> handleInvalidFormat(InvalidFormatException ex) {
    String field = ex.getPath().isEmpty() ? "unknown" : ex.getPath().getFirst().getPropertyName();
    if (UUID.class.equals(ex.getTargetType())) {
      return badRequest(String.format("The %s '%s' has not been found", field, ex.getValue()));
    }
    return badRequest(String.format("Invalid value '%s' for field '%s'", ex.getValue(), field));
  }

  private ResponseEntity<BaseResponse<?>> handleUnrecognizedProperty(UnrecognizedPropertyException ex) {
    return badRequest(String.format("Unknown property '%s' is not allowed", ex.getPropertyName()));
  }

  private ResponseEntity<BaseResponse<?>> handleEmptyBody() {
    return badRequest("Request body is required");
  }

  private boolean isEmptyBody(HttpMessageNotReadableException ex) {
    return ex.getMessage() != null && ex.getMessage().contains("Required request body is missing");
  }

  private ResponseEntity<BaseResponse<?>> badRequest(String message) {
    return ResponseEntity.badRequest().body(BaseResponse.fail(message));
  }

}