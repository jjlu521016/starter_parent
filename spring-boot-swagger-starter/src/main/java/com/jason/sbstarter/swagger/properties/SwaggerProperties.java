package com.jason.sbstarter.swagger.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;

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
@Data
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

    private Contact contact = new Contact();

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


    /**
     * @author JasonLu
     */
    @Data
    @NoArgsConstructor
    public static class Contact implements Serializable {


        private static final long serialVersionUID = -372421614509053212L;

        private String name;
        private String url;
        private String email;

    }

    @Data
    @NoArgsConstructor
    public static class DocketInfo implements Serializable {


        private static final long serialVersionUID = 1992824650531028895L;
        /**
         * 标题
         **/
        private String title = "";
        /**
         * 描述
         **/
        private String description = "";
        /**
         * 版本
         **/
        private String version = "";
        /**
         * 许可证
         **/
        private String license = "";
        /**
         * 许可证URL
         **/
        private String licenseUrl = "";
        /**
         * 服务条款URL
         **/
        private String termsOfServiceUrl = "";

        private io.swagger.models.Contact contact = new io.swagger.models.Contact();

        /**
         * swagger会解析的包路径
         **/
        private String basePackage = "";

        /**
         * swagger会解析的url规则
         **/
        private List<String> basePath = new ArrayList<>();
        /**
         * 在basePath基础上需要排除的url规则
         **/
        private List<String> excludePath = new ArrayList<>();

        private List<GlobalOperationParameter> globalOperationParameters;

        /**
         * 忽略的参数类型
         **/
        private List<Class<?>> ignoredParameterTypes = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    public static class DocketParamInfo implements Serializable {


        private static final long serialVersionUID = -818353949655464421L;
        /**
         * 解析的url
         */
        private List<String> basePath = new ArrayList<>();

        /**
         * 在basePath中排除的url
         */
        private List<String> excludePath = new ArrayList<>();

        /**
         * 全局参数配置
         **/
        private List<Parameter> globalOperationParameters;


        /**
         *
         */
        private String groupName;

        /**
         * 需要配置的basePackage
         */
        private String basePackage = "";

        /**
         * 忽略参数类型
         */
        private List<Class<?>> ignoredParameterTypes = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    public class GlobalOperationParameter implements Serializable {

        private static final long serialVersionUID = -2956353065902392944L;
        /**
         * 参数名
         **/
        private String name;

        /**
         * 描述信息
         **/
        private String description;

        /**
         * 指定参数类型
         **/
        private String modelRef;

        /**
         * 参数放在哪个地方:header,query,path,body.form
         **/
        private String parameterType;

        /**
         * 参数是否必须传
         **/
        private String required;
    }

    @Data
    @NoArgsConstructor
    public class GlobalResponseMessage implements Serializable {


        private static final long serialVersionUID = -8762843045350035681L;
        /**
         * POST 响应消息体
         **/
        List<GlobalResponseMessageBody> post = new ArrayList<>();

        /**
         * GET 响应消息体
         **/
        List<GlobalResponseMessageBody> get = new ArrayList<>();

        /**
         * PUT 响应消息体
         **/
        List<GlobalResponseMessageBody> put = new ArrayList<>();

        /**
         * PATCH 响应消息体
         **/
        List<GlobalResponseMessageBody> patch = new ArrayList<>();

        /**
         * DELETE 响应消息体
         **/
        List<GlobalResponseMessageBody> delete = new ArrayList<>();

        /**
         * HEAD 响应消息体
         **/
        List<GlobalResponseMessageBody> head = new ArrayList<>();

        /**
         * OPTIONS 响应消息体
         **/
        List<GlobalResponseMessageBody> options = new ArrayList<>();

        /**
         * TRACE 响应消息体
         **/
        List<GlobalResponseMessageBody> trace = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    public static class GlobalResponseMessageBody implements Serializable {

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
    }

    @Data
    @NoArgsConstructor
    public static class UIConfig implements Serializable {


        private static final long serialVersionUID = 6920835960053111075L;
        private String apiSorter = "alpha";

        /**
         * 是否启用json编辑器
         **/
        private Boolean jsonEditor = false;
        /**
         * 是否显示请求头信息
         **/
        private Boolean showRequestHeaders = true;
        /**
         * 支持页面提交的请求类型
         **/
        private String submitMethods = "get,post,put,delete,patch";
        /**
         * 请求超时时间
         **/
        private Long requestTimeout = 10000L;

        private Boolean deepLinking;
        private Boolean displayOperationId;
        private Integer defaultModelsExpandDepth;
        private Integer defaultModelExpandDepth;
        private ModelRendering defaultModelRendering;

        /**
         * 是否显示请求耗时，默认false
         */
        private Boolean displayRequestDuration = true;
        /**
         * 可选 none | list
         */
        private DocExpansion docExpansion;
        /**
         * Boolean=false OR String
         */
        private Object filter;
        private Integer maxDisplayedTags;
        private OperationsSorter operationsSorter;
        private Boolean showExtensions;
        private TagsSorter tagsSorter;

        /**
         * Network
         */
        private String validatorUrl;
    }

    @Data
    @NoArgsConstructor
    public static class Authorization implements Serializable {


        private static final long serialVersionUID = -7081557268618888471L;
        /**
         * 鉴权策略ID，对应 SecurityReferences ID
         */
        private String name = "Authorization";

        /**
         * 鉴权传递的Header参数
         */
        private String keyName = "TOKEN";

        /**
         * 需要开启鉴权URL的正则
         */
        private String authRegex = "^.*$";
    }
}
