package com.mitail.base;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
@Data
public class Result <T>{

    private Integer code;

    private String message;

    private T data;

    public static <T> Result error(Message message,T data){
        return new Result<>().setCode(message.code()).setMessage(message.message()).setData(data);
    }

    public static<T> Result sussces(T data){
        return new Result<>().setCode(200).setMessage("返回成功").setData(data);
    }
}
