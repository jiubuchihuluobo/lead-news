package com.surge.common.dto;

import com.surge.common.enums.HttpCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {

    private String host;

    private Integer code = 200;

    private String errorMessage;

    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.errorMessage = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.errorMessage = msg;
        this.data = data;
    }

    // 成功响应
    public static <T> ResponseResult<T> okResult(int code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    public static <T> ResponseResult<T> okResult(T data) {
        return ResponseResult.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getErrorMessage(), data);
    }

    public static <T> ResponseResult<T> okResult() {
        return ResponseResult.okResult(null);
    }

    // 错误响应
    public static <T> ResponseResult<T> errorResult(int code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public static <T> ResponseResult<T> errorResult(HttpCodeEnum httpCodeEnum) {
        return ResponseResult.errorResult(httpCodeEnum.getCode(), httpCodeEnum.getErrorMessage());
    }

    public static ResponseResult<Object> errorResult(HttpCodeEnum httpCodeEnum, String errorMessage) {
        return ResponseResult.errorResult(httpCodeEnum.getCode(), errorMessage);
    }

}
