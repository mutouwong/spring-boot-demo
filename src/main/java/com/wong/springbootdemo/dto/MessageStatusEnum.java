package com.wong.springbootdemo.dto;

/**
 * 消息状态枚举
 */
public enum MessageStatusEnum {

    SUCCESS("000000","成功"),
    FAILD("999999","服务执行异常");

    private MessageStatusEnum(String returnCode, String returnDesc){
        this.returnCode = returnCode;
        this.returnDesc = returnDesc;
    }

    private String returnCode;
    private String returnDesc;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }
}
