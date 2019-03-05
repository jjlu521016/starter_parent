package com.jason.sbstarter.swagger.handler;

import com.jason.sbstarter.swagger.bean.Authorization;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.ApiKeyVehicle;

import java.util.Collections;
import java.util.List;

public class Authhandler {

    public static ApiKey apiKey(Authorization authorization) {
        return new ApiKey(authorization.getName(),
                authorization.getKeyName(),
                ApiKeyVehicle.HEADER.getValue());
    }

    /**
     * 配置默认的全局鉴权策略的开关，以及通过正则表达式进行匹配；默认 ^.*$ 匹配所有URL
     * 其中 securityReferences 为配置启用的鉴权策略
     *
     * @return
     */
    public static SecurityContext securityContext(Authorization authorization) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth(authorization))
                .forPaths(PathSelectors.regex(authorization.getAuthRegex()))
                .build();
    }

    /**
     * 配置默认的全局鉴权策略；其中返回的 SecurityReference 中，reference 即为ApiKey对象里面的name，保持一致才能开启全局鉴权
     *
     * @return
     */
    private static List<SecurityReference> defaultAuth(Authorization authorization) {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(SecurityReference.builder()
                .reference(authorization.getName())
                .scopes(authorizationScopes).build());
    }

}
