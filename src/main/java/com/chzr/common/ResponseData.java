package com.chzr.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class ResponseData {
    private Integer code;
    private Boolean success;
    private Object data;
    private String msg;

    public ResponseData(){
        this.code = 200;
        this.success = true;
        this.data = "asdf";
        this.msg = "aaaa";
    }

    public ResponseData(Integer code, Boolean success, Object data, String msg){
        this.code = code;
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
}
