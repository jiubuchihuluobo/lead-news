package com.surge.exception;

import com.surge.enums.HttpCodeEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final HttpCodeEnum httpCodeEnum;

    private final String message;

    public CustomException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getErrorMessage());
        this.httpCodeEnum = httpCodeEnum;
        this.message = httpCodeEnum.getErrorMessage();
    }

    public CustomException(HttpCodeEnum httpCodeEnum, String message) {
        super(message);
        this.httpCodeEnum = httpCodeEnum;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
