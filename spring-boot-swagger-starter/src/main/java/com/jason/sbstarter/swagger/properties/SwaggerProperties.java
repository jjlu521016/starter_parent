package com.jason.sbstarter.swagger.properties;

import com.jason.sbstarter.swagger.bean.Authorization;
import com.jason.sbstarter.swagger.bean.ContactNew;
import com.jason.sbstarter.swagger.bean.DocketInfo;
import com.jason.sbstarter.swagger.bean.GlobalOperationParameter;
import com.jason.sbstarter.swagger.bean.GlobalResponseMessage;
import com.jason.sbstarter.swagger.bean.UIConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author JasonLu
 */
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties implements Serializable {
    /**
     * 是否开启swagger
     **/
    private Boolean enabled = false;

    /**
     * 需要配置的basePackage
     */
    private String basePackage = "";

    /**
     * 标题
     */
    private String title = "";

    /**
     * 描述
     */
    private String description = "";

    /**
     * 版本号
     */
    private String version = "";
    /**
     * 许可证
     */
    private String license = "";

    /**
     * 许可证url
     */
    private String licenseUrl = "";

    /**
     * 服务条款
     */
    private String termsServiceUrl = "";

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 忽略参数类型
     */
    private List<Class<?>> ignoredParameterTypes = new ArrayList<>();

    private ContactNew contactNew = new ContactNew();

    /**
     * 解析的url
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * 在basePath中排除的url
     */
    private List<String> excludePath = new ArrayList<>();

    /**
     * 分组文档
     **/
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();

    /**
     * 全局参数配置
     **/
    private List<GlobalOperationParameter> globalOperationParameters;

    /**
     * 页面功能配置
     **/
    private UIConfig uiConfig = new UIConfig();

    /**
     * 是否使用默认预定义的响应消息 ，默认 true
     **/
    private Boolean applyDefaultResponseMessages = true;

    /**
     * 全局响应消息
     **/
    private GlobalResponseMessage globalResponseMessage;

    /**
     * 全局统一鉴权配置
     **/
    private Authorization authorization = new Authorization();

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getTermsServiceUrl() {
        return termsServiceUrl;
    }

    public void setTermsServiceUrl(String termsServiceUrl) {
        this.termsServiceUrl = termsServiceUrl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Class<?>> getIgnoredParameterTypes() {
        return ignoredParameterTypes;
    }

    public void setIgnoredParameterTypes(List<Class<?>> ignoredParameterTypes) {
        this.ignoredParameterTypes = ignoredParameterTypes;
    }

    public ContactNew getContactNew() {
        return contactNew;
    }

    public void setContactNew(ContactNew contactNew) {
        this.contactNew = contactNew;
    }

    public List<String> getBasePath() {
        return basePath;
    }

    public void setBasePath(List<String> basePath) {
        this.basePath = basePath;
    }

    public List<String> getExcludePath() {
        return excludePath;
    }

    public void setExcludePath(List<String> excludePath) {
        this.excludePath = excludePath;
    }

    public Map<String, DocketInfo> getDocket() {
        return docket;
    }

    public void setDocket(Map<String, DocketInfo> docket) {
        this.docket = docket;
    }

    public List<GlobalOperationParameter> getGlobalOperationParameters() {
        return globalOperationParameters;
    }

    public void setGlobalOperationParameters(List<GlobalOperationParameter> globalOperationParameters) {
        this.globalOperationParameters = globalOperationParameters;
    }

    public UIConfig getUiConfig() {
        return uiConfig;
    }

    public void setUiConfig(UIConfig uiConfig) {
        this.uiConfig = uiConfig;
    }

    public Boolean getApplyDefaultResponseMessages() {
        return applyDefaultResponseMessages;
    }

    public void setApplyDefaultResponseMessages(Boolean applyDefaultResponseMessages) {
        this.applyDefaultResponseMessages = applyDefaultResponseMessages;
    }

    public GlobalResponseMessage getGlobalResponseMessage() {
        return globalResponseMessage;
    }

    public void setGlobalResponseMessage(GlobalResponseMessage globalResponseMessage) {
        this.globalResponseMessage = globalResponseMessage;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }
}
