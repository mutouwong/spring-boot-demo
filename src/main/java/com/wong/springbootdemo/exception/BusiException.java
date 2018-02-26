package com.wong.springbootdemo.exception;

/**
 * @Description: 自定义异常
 * @Author: gang.wang
 * @Date: Created in 上午9:45 2018/2/26
 */
public class BusiException extends RuntimeException {

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusiException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
