package com.ruichen.restful.common.response;

import java.util.Objects;

public class BaseResponseBuilder<T> {


    public static final String SUCCESS_CODE = "200";

    protected BaseResponse<T> response;

    public BaseResponseBuilder success() {
        response = new BaseResponse();
        response.setStatus(true);
        response.setCode(SUCCESS_CODE);
        return this;
    }

    public BaseResponseBuilder success(String message) {
        response = new BaseResponse();
        response.setStatus(true);
        response.setCode(SUCCESS_CODE);
        response.setMessage(message);
        return this;
    }

    public BaseResponseBuilder fail(Object code, String message) {
        response = new BaseResponse();
        response.setStatus(false);
        response.setCode(String.valueOf(code));
        response.setMessage(message);
        return this;
    }

    public BaseResponseBuilder data(T data) {
        Objects.requireNonNull(response, "success or fail method should be called before other method!");
        this.response.setData(data);
        return this;
    }

    public BaseResponse build() {
        return response == null ? new BaseResponse() : response;
    }
}
