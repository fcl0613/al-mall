package com.al.almall.entity;

import com.al.almall.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回实体类
 * @param <T>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T>Result<T> success(T data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static Result failed() {
        return new Result(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
    }

    public static Result failed(String message) {
        return new Result(ResultCode.FAILED.getCode(), message, null);
    }
}
