package com.al.almall.exception;

public class RequestException extends RuntimeException{
    private Integer code;
    private String message;

    public RequestException() {
        super();
    }

    public RequestException(String errorMsg) {
        super("{code:" + 500 + ",errorMsg:" + errorMsg + "}");
        this.code = 500;
        this.message = errorMsg;
    }

    public RequestException(Integer code, String errorMsg) {
        super("{code:" + code + ",errorMsg:" + errorMsg + "}");
        this.code = code;
        this.message = errorMsg;
    }

    public String getErrorMsg() {
        return this.message;
    }

    public void setErrorMsg(String errorMsg) {
        this.message = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
