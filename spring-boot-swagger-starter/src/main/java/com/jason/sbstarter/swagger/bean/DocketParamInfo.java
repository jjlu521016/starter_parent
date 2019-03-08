//package com.jason.sbstarter.swagger.bean;
//
//import springfox.documentation.service.Parameter;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author JasonLu
// */
//public class DocketParamInfo implements Serializable {
//
//
//    private static final long serialVersionUID = -818353949655464421L;
//    /**
//     * 解析的url
//     */
//    private List<String> basePath = new ArrayList<>();
//
//    /**
//     * 在basePath中排除的url
//     */
//    private List<String> excludePath = new ArrayList<>();
//
//    /**
//     * 全局参数配置
//     **/
//    private List<Parameter> globalOperationParameters;
//
//
//    /**
//     *
//     */
//    private String groupName;
//
//    /**
//     * 需要配置的basePackage
//     */
//    private String basePackage = "";
//
//    /**
//     * 忽略参数类型
//     */
//    private List<Class<?>> ignoredParameterTypes = new ArrayList<>();
//
//
//    public List<String> getBasePath() {
//        return basePath;
//    }
//
//    public void setBasePath(List<String> basePath) {
//        this.basePath = basePath;
//    }
//
//    public List<String> getExcludePath() {
//        return excludePath;
//    }
//
//    public void setExcludePath(List<String> excludePath) {
//        this.excludePath = excludePath;
//    }
//
//    public List<Parameter> getGlobalOperationParameters() {
//        return globalOperationParameters;
//    }
//
//    public void setGlobalOperationParameters(List<Parameter> globalOperationParameters) {
//        this.globalOperationParameters = globalOperationParameters;
//    }
//
//
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    public String getBasePackage() {
//        return basePackage;
//    }
//
//    public void setBasePackage(String basePackage) {
//        this.basePackage = basePackage;
//    }
//
//    public List<Class<?>> getIgnoredParameterTypes() {
//        return ignoredParameterTypes;
//    }
//
//    public void setIgnoredParameterTypes(List<Class<?>> ignoredParameterTypes) {
//        this.ignoredParameterTypes = ignoredParameterTypes;
//    }
//}
