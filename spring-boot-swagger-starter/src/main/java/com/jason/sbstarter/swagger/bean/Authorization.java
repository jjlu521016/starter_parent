//package com.jason.sbstarter.swagger.bean;
//
//import java.io.Serializable;
//
///**
// * @author JasonLu
// */
//public class Authorization implements Serializable {
//
//
//    private static final long serialVersionUID = -7081557268618888471L;
//    /**
//     * 鉴权策略ID，对应 SecurityReferences ID
//     */
//    private String name = "Authorization";
//
//    /**
//     * 鉴权传递的Header参数
//     */
//    private String keyName = "TOKEN";
//
//    /**
//     * 需要开启鉴权URL的正则
//     */
//    private String authRegex = "^.*$";
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getKeyName() {
//        return keyName;
//    }
//
//    public void setKeyName(String keyName) {
//        this.keyName = keyName;
//    }
//
//    public String getAuthRegex() {
//        return authRegex;
//    }
//
//    public void setAuthRegex(String authRegex) {
//        this.authRegex = authRegex;
//    }
//}
