package com.jason.sbstarter.swagger.config;

import com.jason.sbstarter.swagger.bean.DocketInfo;
import com.jason.sbstarter.swagger.bean.UIConfig;
import com.jason.sbstarter.swagger.handler.GroupDocketHandler;
import com.jason.sbstarter.swagger.properties.SwaggerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.List;
import java.util.Map;

@Configuration
@Import({
        Swagger2Configuration.class
})
public class SwaggerAutoConfig implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean
    public UiConfiguration uiConfiguration(SwaggerProperties swaggerProperties) {
        UIConfig uiConfig = swaggerProperties.getUiConfig();
        return UiConfigurationBuilder.builder()
                .deepLinking(uiConfig.getDeepLinking())
                .defaultModelExpandDepth(uiConfig.getDefaultModelExpandDepth())
                .defaultModelRendering(uiConfig.getDefaultModelRendering())
                .defaultModelsExpandDepth(uiConfig.getDefaultModelsExpandDepth())
                .displayOperationId(uiConfig.getDisplayOperationId())
                .displayRequestDuration(uiConfig.getDisplayRequestDuration())
                .docExpansion(uiConfig.getDocExpansion())
                .maxDisplayedTags(uiConfig.getMaxDisplayedTags())
                .operationsSorter(uiConfig.getOperationsSorter())
                .showExtensions(uiConfig.getShowExtensions())
                .tagsSorter(uiConfig.getTagsSorter())
                .validatorUrl(uiConfig.getValidatorUrl())
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(UiConfiguration.class)
    @ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
    public List<Docket> createRestApi(SwaggerProperties swaggerProperties) {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        Map<String, DocketInfo> docketMap = swaggerProperties.getDocket();
        if (docketMap.size() == 0) {
            //没有设置分组
            return GroupDocketHandler.noGroup(swaggerProperties, configurableBeanFactory);
        } else {
            //设置分组
            return GroupDocketHandler.hasGroup(swaggerProperties, configurableBeanFactory);
        }
    }
}
