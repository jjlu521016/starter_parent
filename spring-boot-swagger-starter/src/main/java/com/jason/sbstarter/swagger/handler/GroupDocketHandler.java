package com.jason.sbstarter.swagger.handler;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.jason.sbstarter.swagger.properties.SwaggerProperties;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author JasonLu
 */
public class GroupDocketHandler {
    /**
     * 没有配置分组
     *
     * @param swaggerProperties
     * @param configurableBeanFactory
     * @return
     */
    public static List<Docket> noGroup(SwaggerProperties swaggerProperties, ConfigurableBeanFactory configurableBeanFactory) {
        List<Docket> docketList = new LinkedList<>();
        //处理apiInfo对象
        ApiInfo apiInfo = apiInfoBuilder(swaggerProperties, null);
        List<Parameter> globalOperationParameters = GlobalResponsehandler.buildGlobalOperationParameters(
                swaggerProperties.getGlobalOperationParameters());

        SwaggerProperties.DocketParamInfo docketParamInfo = new SwaggerProperties.DocketParamInfo();
        docketParamInfo.setBasePath(swaggerProperties.getBasePath());
        docketParamInfo.setExcludePath(swaggerProperties.getExcludePath());
        docketParamInfo.setBasePackage(swaggerProperties.getBasePackage());
        docketParamInfo.setIgnoredParameterTypes(swaggerProperties.getIgnoredParameterTypes());
        docketParamInfo.setGlobalOperationParameters(globalOperationParameters);
        Docket docket = docketBuildrOperation(docketParamInfo, apiInfo, swaggerProperties);

        configurableBeanFactory.registerSingleton("defaultDocket", docket);
        docketList.add(docket);
        return docketList;
    }

    public static List<Docket> hasGroup(SwaggerProperties swaggerProperties, ConfigurableBeanFactory configurableBeanFactory) {
        List<Docket> docketList = new LinkedList<>();
        Map<String, SwaggerProperties.DocketInfo> docketMap = swaggerProperties.getDocket();

        for (String groupName : docketMap.keySet()) {
            SwaggerProperties.DocketInfo docketInfo = swaggerProperties.getDocket().get(groupName);
            ApiInfo apiInfo = apiInfoBuilder(swaggerProperties, docketInfo);
            List<SwaggerProperties.GlobalOperationParameter> swGlobalParameters = swaggerProperties.getGlobalOperationParameters();
            List<SwaggerProperties.GlobalOperationParameter> docketGlobalParameters = docketInfo.getGlobalOperationParameters();
            List<Parameter> globalOperationParameters = GlobalResponsehandler.assemblyGlobalOperationParameters(swGlobalParameters,
                    docketGlobalParameters);

            SwaggerProperties.DocketParamInfo docketParamInfo = new SwaggerProperties.DocketParamInfo();
            docketParamInfo.setBasePath(docketInfo.getBasePath());
            docketParamInfo.setGroupName(groupName);
            docketParamInfo.setExcludePath(docketInfo.getExcludePath());
            docketParamInfo.setBasePackage(docketInfo.getBasePackage());
            docketParamInfo.setIgnoredParameterTypes(docketInfo.getIgnoredParameterTypes());
            docketParamInfo.setGlobalOperationParameters(globalOperationParameters);
            Docket docket = docketBuildrOperation(docketParamInfo, apiInfo, swaggerProperties);
            configurableBeanFactory.registerSingleton(groupName, docket);
            docketList.add(docket);

        }
        return docketList;
    }


    private static ApiInfo apiInfoBuilder(SwaggerProperties swaggerProperties, SwaggerProperties.DocketInfo docketInfo) {
        Contact contact = null;
        if (null == docketInfo) {
            contact = new Contact(swaggerProperties.getContact().getName(),
                    swaggerProperties.getContact().getUrl(),
                    swaggerProperties.getContact().getEmail());
        } else {
            contact = buildContact(docketInfo, swaggerProperties.getContact());
        }
        return new ApiInfoBuilder()
                .contact(contact)
                .description(swaggerProperties.getDescription())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .termsOfServiceUrl(swaggerProperties.getTermsServiceUrl())
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getVersion())
                .build();
    }

    private static Contact buildContact(SwaggerProperties.DocketInfo docketInfo, SwaggerProperties.Contact contactNew) {
        return new Contact(
                StringUtils.isEmpty(docketInfo.getContact().getName()) ? contactNew.getName() : docketInfo.getContact().getName(),
                StringUtils.isEmpty(docketInfo.getContact().getUrl()) ? contactNew.getUrl() : docketInfo.getContact().getUrl(),
                StringUtils.isEmpty(docketInfo.getContact().getEmail()) ? contactNew.getEmail() : docketInfo.getContact().getEmail()
        );
    }

    private static List<Predicate<String>> pathHandler(List<String> pathList) {
        List<Predicate<String>> predicateList = new ArrayList();
        for (String path : pathList) {
            predicateList.add(PathSelectors.ant(path));
        }
        return predicateList;
    }


    private static Docket docketBuildrOperation(SwaggerProperties.DocketParamInfo docketParamInfo, ApiInfo apiInfo, SwaggerProperties swaggerProperties) {

        // base-path处理 没有配置任何path的时候，解析/**
        if (docketParamInfo.getBasePath().isEmpty()) {
            docketParamInfo.getBasePath().add("/**");
        }
        List<Predicate<String>> basePath = pathHandler(docketParamInfo.getBasePath());
        List<Predicate<String>> excludePath = pathHandler(docketParamInfo.getExcludePath());

        Docket docketForBuilder = new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo)
                .securitySchemes(Collections.singletonList(Authhandler.apiKey(swaggerProperties.getAuthorization())))
                .securityContexts(Collections.singletonList(Authhandler.securityContext(swaggerProperties.getAuthorization())))
                .globalOperationParameters(docketParamInfo.getGlobalOperationParameters());

        // 全局响应消息
        if (!swaggerProperties.getApplyDefaultResponseMessages()) {
            GlobalResponsehandler.buildGlobalResponseMessage(swaggerProperties, docketForBuilder);
        }
        Docket docket = null;
        if (!StringUtils.isEmpty(docketParamInfo.getGroupName())) {
            docket = docketForBuilder.groupName(docketParamInfo.getGroupName());
        } else {
            docket = docketForBuilder;
        }
        docket.select().apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath))).build();

        /* ignoredParameterTypes **/
        Class<?>[] array = new Class[docketParamInfo.getIgnoredParameterTypes().size()];
        Class<?>[] ignoredParameterTypes = docketParamInfo.getIgnoredParameterTypes().toArray(array);
        docket.ignoredParameterTypes(ignoredParameterTypes);
        return docket;

    }

}
