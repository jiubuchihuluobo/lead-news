package com.surge.exception;

import com.surge.common.enums.HttpCodeEnum;


public class CustomException extends RuntimeException {

    private final HttpCodeEnum httpCodeEnum;

    private final String message;

    /**
     * 构造一个新的CustomException实例，使用给定的http状态码枚举来设置异常消息。
     *
     * @param httpCodeEnum HTTP状态码枚举
     */
    public CustomException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getErrorMessage());
        this.httpCodeEnum = httpCodeEnum;
        this.message = httpCodeEnum.getErrorMessage();
    }

    /**
     * 构造一个新的CustomException实例，使用给定的http状态码枚举和消息来设置异常消息。
     *
     * @param httpCodeEnum HTTP状态码枚举
     * @param message      异常消息
     */
    public CustomException(HttpCodeEnum httpCodeEnum, String message) {
        super(message);
        this.httpCodeEnum = httpCodeEnum;
        this.message = message;
    }

}
