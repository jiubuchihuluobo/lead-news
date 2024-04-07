package com.surge.exception;

import com.surge.enums.HttpCodeEnum;

public class RaiseException {

    public static void raise(HttpCodeEnum codeEnum) {
        throw new CustomException(codeEnum, codeEnum.getErrorMessage());
    }

    public static void raise(HttpCodeEnum codeEnum, String msg) {
        throw new CustomException(codeEnum, msg);
    }

}
