package com.photo.center.util;

import com.photo.center.result.JsonResult;
import com.photo.center.result.ResultCode;

/**
 * @author: zeng wu
 * @create: 2020-06-09 20:52
 * @description: 返回工具
 */
public class JsonTool {

    public static JsonResult success(){
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data){
        return new JsonResult<T>(true,data);
    }

    public static JsonResult fail(){
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultCode){
        return new JsonResult(false,resultCode);
    }
}
