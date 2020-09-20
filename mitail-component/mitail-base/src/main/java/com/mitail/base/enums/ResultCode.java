package com.mitail.base.enums;

import com.mitail.base.model.Message;

public enum ResultCode implements Message {

    SYSTEM_ERROR(400,"运行错误联系管理员"),PARAM_ERROR(1001,"参数传递错误");
    private Integer code;

    private String message;
    ResultCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
