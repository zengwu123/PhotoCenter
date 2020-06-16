package com.photo.center.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: zeng wu
 * @create: 2020-06-09 20:17
 * @description: 统一返回JSON定义
 */
@Data
@ToString
public class JsonResult<T>  implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
        this.code =success?ResultCode.SUCCESS.getCode():ResultCode.COMMON_FAIL.getCode();
        this.message =success?ResultCode.SUCCESS.getMessage():ResultCode.COMMON_FAIL.getMessage();
    }

    public JsonResult(boolean success, ResultCode resultCode) {
        this.success = success;
        this.code =success?ResultCode.SUCCESS.getCode():(resultCode==null?ResultCode.COMMON_FAIL.getCode():resultCode.getCode());
        this.message =success?ResultCode.SUCCESS.getMessage():(resultCode==null?ResultCode.COMMON_FAIL.getMessage():resultCode.getMessage());
    }

    public JsonResult(Boolean success, T data) {
        this.success = success;
        this.code =success?ResultCode.SUCCESS.getCode():ResultCode.COMMON_FAIL.getCode();
        this.message =success?ResultCode.SUCCESS.getMessage():ResultCode.COMMON_FAIL.getMessage();
        this.data=data;
    }

    public JsonResult(Boolean success, ResultCode resultCode, T data) {
        this.success = success;
        this.code =success?ResultCode.SUCCESS.getCode():(resultCode==null?ResultCode.COMMON_FAIL.getCode():resultCode.getCode());
        this.message =success?ResultCode.SUCCESS.getMessage():(resultCode==null?ResultCode.COMMON_FAIL.getMessage():resultCode.getMessage());
        this.data=data;
    }
}
