package com.surge.exception;

import com.surge.dto.ResponseResult;
import com.surge.enums.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice
@Slf4j
public class CatchException {

    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> exceptionHandler(Exception exception) {
        log.error(exception.toString());
        return ResponseResult.errorResult(HttpCodeEnum.SERVER_ERROR, "请稍后重试");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseResult<Object> customExceptionHandler(CustomException exception) {
        log.error(exception.toString());
        return ResponseResult.errorResult(exception.getHttpCodeEnum());
    }

}
