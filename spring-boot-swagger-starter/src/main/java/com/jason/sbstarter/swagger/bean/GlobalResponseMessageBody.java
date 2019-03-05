package com.jason.sbstarter.swagger.bean;

import java.io.Serializable;

/**
 * @author JasonLu
 */
public class GlobalResponseMessageBody implements Serializable {

    private static final long serialVersionUID = 4148865467485801281L;
    /**
     * 响应码
     **/
    private int code;

    /**
     * 响应消息
     **/
    private String message;

    /**
     * 响应体
     **/
    private String modelRef;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getModelRef() {
        return modelRef;
    }

    public void setModelRef(String modelRef) {
        this.modelRef = modelRef;
    }
}
