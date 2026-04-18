package com.dasaczzz.tempy.lib;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {

  private boolean success;

  private T data;

  public static <T> BaseResponse<T> ok(T data) {
    return BaseResponse.<T>builder().success(true).data(data).build();
  }

  public static <S> BaseResponse<S> fail(S data) {
    return BaseResponse.<S>builder().success(false).data(data).build();
  }

}
