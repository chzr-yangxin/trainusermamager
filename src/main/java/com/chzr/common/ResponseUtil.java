package com.chzr.common;

public class ResponseUtil {
    public static ResponseData OK(Object data){
        return new ResponseData(200, true, data, "");
    }

    public static ResponseData FAIL(String msg){
        return new ResponseData(200, false, null, msg);
    }
}
