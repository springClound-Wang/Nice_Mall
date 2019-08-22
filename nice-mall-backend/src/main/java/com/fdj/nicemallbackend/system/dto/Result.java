package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

import java.util.HashMap;

/**
 * @Classname Result
 * @Description TODO
 * @Date 19-8-14 下午5:00
 * @Created by xns
 */
@Data
public class Result {
    private boolean status;
    private String message;
    private Object data;

    private Result setResult(boolean status,String message,Object data){
        this.status = status;
        this.message = message;
        this.data = data;
        return this;
    }

    public Result success(String message){
        return setResult(true,message,"无数据");
    }

    public Result success(Object data){
        return setResult(true,"成功",data);
    }

    public Result success(Object data,String message){
        return setResult(true,message,data);
    }

    public Result fail(Object data,String message){
        return setResult(false,message,data);
    }

    public Result fail(String message){
        return setResult(false,message,"无数据");
    }

}
