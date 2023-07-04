package com.al.almall.exception;

import com.al.almall.entity.Result;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = RequestException.class)
    public Result handle(RequestException e) {
        if (e.getCode() != null) {
            return Result.failed(e.getErrorMsg());
        }
        return Result.failed(e.getMessage());
    }



    @ResponseBody
    @ExceptionHandler(value = MalformedJwtException.class)
    public Result handle(MalformedJwtException e) {
        return Result.failed("权限解析错误，请重新登录");
    }
}
