# <center>使用说明</center>
# 1. 版本说明
- JDK 1.8
- SpringBoot 2.1.3.RELEASE
- swagger2(SpringFox) 2.9.2

# 2. 使用说明
## 2.1 引入依赖(没有上传maven仓库)
```xml
<dependency>
	    <groupId>com.jason.sbstarter</groupId>
        <artifactId>spring-boot-swagger-starter</artifactId>
	    <version>${version}</version>
</dependency
```
## 2. 在应用主类中增加@EnableSwagger2注解
```java
@EnableSwagger2
@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
```
默认情况下就能产生所有当前Spring MVC加载的请求映射文档。

## 2.3 参数配置
- 默认配置
```yaml
swagger.enabled=是否启用swagger，默认：true
swagger.title=标题
swagger.description=描述
swagger.version=版本
swagger.license=许可证
swagger.licenseUrl=许可证URL
swagger.termsOfServiceUrl=服务条款URL
swagger.contact.name=维护人
swagger.contact.url=维护人URL
swagger.contact.email=维护人email
swagger.base-package=swagger扫描的基础包，默认：全扫描
swagger.base-path=需要处理的基础URL规则，默认：/**
swagger.exclude-path=需要排除的URL规则，默认：空
swagger.host=文档的host信息，默认：空
swagger.globalOperationParameters[0].name=参数名
swagger.globalOperationParameters[0].description=描述信息
swagger.globalOperationParameters[0].modelRef=指定参数类型
swagger.globalOperationParameters[0].parameterType=指定参数存放位置,可选header,query,path,body.form
swagger.globalOperationParameters[0].required=指定参数是否必传，true,false
```
- Path规则说明
`swagger.base-path`和`swagger.exclude-path`使用ANT规则配置。
通常我们可以这样设置：
management.context-path=/ops
swagger.base-path=/**
swagger.exclude-path=/ops/**, /error
上面的设置将解析所有除了/ops/开始以及spring boot自带/error请求路径。
其中，exclude-path可以配合management.context-path=/ops设置的spring boot actuator的context-path来排除所有监控端点。
- 分组配置
当我们一个项目的API非常多的时候，我们希望对API文档实现分组
```yaml
swagger.docket.<groupName>.title=标题
swagger.docket.<groupName>.description=描述
swagger.docket.<groupName>.version=版本
swagger.docket.<groupName>.license=许可证
swagger.docket.<groupName>.licenseUrl=许可证URL
swagger.docket.<groupName>.termsOfServiceUrl=服务条款URL
swagger.docket.<groupName>.contact.name=维护人
swagger.docket.<groupName>.contact.url=维护人URL
swagger.docket.<groupName>.contact.email=维护人email
swagger.docket.<groupName>.base-package=swagger扫描的基础包，默认：全扫描
swagger.docket.<groupName>.base-path=需要处理的基础URL规则，默认：/**
swagger.docket.<groupName>.exclude-path=需要排除的URL规则，默认：空
swagger.docket.<groupName>.name=参数名
swagger.docket.<groupName>.modelRef=指定参数类型
swagger.docket.<groupName>.parameterType=指定参数存放位置,可选header,query,path,body.form
swagger.docket.<groupName>.required=true=指定参数是否必传，true,false
swagger.docket.<groupName>.globalOperationParameters[0].name=参数名
swagger.docket.<groupName>.globalOperationParameters[0].description=描述信息
swagger.docket.<groupName>.globalOperationParameters[0].modelRef=指定参数存放位置,可选header,query,path,body.form
swagger.docket.<groupName>.globalOperationParameters[0].parameterType=指定参数是否必传，true,false
```
说明：<groupName>为swagger文档的分组名称，同一个项目中可以配置多个分组，用来划分不同的API文档。