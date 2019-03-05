package com.jason.sbstarter.swagger.handler;

import com.jason.sbstarter.swagger.properties.SwaggerProperties;
import com.jason.sbstarter.swagger.bean.GlobalOperationParameter;
import com.jason.sbstarter.swagger.bean.GlobalResponseMessage;
import com.jason.sbstarter.swagger.bean.GlobalResponseMessageBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class GlobalResponsehandler {
    public static List<Parameter> buildGlobalOperationParameters(
            List<GlobalOperationParameter> globalOperationParameters) {
        List<Parameter> parameters = new ArrayList();

        if (Objects.isNull(globalOperationParameters)) {
            return parameters;
        }
        for (GlobalOperationParameter globalOperationParameter : globalOperationParameters) {
            parameters.add(new ParameterBuilder()
                    .name(globalOperationParameter.getName())
                    .description(globalOperationParameter.getDescription())
                    .modelRef(new ModelRef(globalOperationParameter.getModelRef()))
                    .parameterType(globalOperationParameter.getParameterType())
                    .required(Boolean.parseBoolean(globalOperationParameter.getRequired()))
                    .build());
        }
        return parameters;
    }


    /**
     * 设置全局响应消息
     *
     * @param swaggerProperties swaggerProperties 支持 POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE
     * @param docketForBuilder  swagger docket builder
     */
    public static void buildGlobalResponseMessage(SwaggerProperties swaggerProperties, Docket docketForBuilder) {

        GlobalResponseMessage globalResponseMessages =
                swaggerProperties.getGlobalResponseMessage();

        // POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE 响应消息体
        List<ResponseMessage> postResponseMessages = getResponseMessageList(globalResponseMessages.getPost());
        List<ResponseMessage> getResponseMessages = getResponseMessageList(globalResponseMessages.getGet());
        List<ResponseMessage> putResponseMessages = getResponseMessageList(globalResponseMessages.getPut());
        List<ResponseMessage> patchResponseMessages = getResponseMessageList(globalResponseMessages.getPatch());
        List<ResponseMessage> deleteResponseMessages = getResponseMessageList(globalResponseMessages.getDelete());
        List<ResponseMessage> headResponseMessages = getResponseMessageList(globalResponseMessages.getHead());
        List<ResponseMessage> optionsResponseMessages = getResponseMessageList(globalResponseMessages.getOptions());
        List<ResponseMessage> trackResponseMessages = getResponseMessageList(globalResponseMessages.getTrace());

        docketForBuilder.useDefaultResponseMessages(swaggerProperties.getApplyDefaultResponseMessages())
                .globalResponseMessage(RequestMethod.POST, postResponseMessages)
                .globalResponseMessage(RequestMethod.GET, getResponseMessages)
                .globalResponseMessage(RequestMethod.PUT, putResponseMessages)
                .globalResponseMessage(RequestMethod.PATCH, patchResponseMessages)
                .globalResponseMessage(RequestMethod.DELETE, deleteResponseMessages)
                .globalResponseMessage(RequestMethod.HEAD, headResponseMessages)
                .globalResponseMessage(RequestMethod.OPTIONS, optionsResponseMessages)
                .globalResponseMessage(RequestMethod.TRACE, trackResponseMessages);
    }

    /**
     * 获取返回消息体列表
     *
     * @param globalResponseMessageBodyList 全局Code消息返回集合
     * @return
     */
    public static List<ResponseMessage> getResponseMessageList(List<GlobalResponseMessageBody> globalResponseMessageBodyList) {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        for (GlobalResponseMessageBody globalResponseMessageBody : globalResponseMessageBodyList) {
            ResponseMessageBuilder responseMessageBuilder = new ResponseMessageBuilder();
            responseMessageBuilder.code(globalResponseMessageBody.getCode()).message(globalResponseMessageBody.getMessage());

            if (!StringUtils.isEmpty(globalResponseMessageBody.getModelRef())) {
                responseMessageBuilder.responseModel(new ModelRef(globalResponseMessageBody.getModelRef()));
            }
            responseMessages.add(responseMessageBuilder.build());
        }

        return responseMessages;
    }

    /**
     * 局部参数按照name覆盖局部参数
     *
     * @param globalOperationParameters
     * @param docketOperationParameters
     * @return
     */
    public static List<Parameter> assemblyGlobalOperationParameters(
            List<GlobalOperationParameter> globalOperationParameters,
            List<GlobalOperationParameter> docketOperationParameters) {

        if (Objects.isNull(docketOperationParameters) || docketOperationParameters.isEmpty()) {
            return buildGlobalOperationParameters(globalOperationParameters);
        }

        Set<String> docketNames = docketOperationParameters.stream()
                .map(GlobalOperationParameter::getName)
                .collect(Collectors.toSet());

        List<GlobalOperationParameter> resultOperationParameters = new ArrayList();

        if (Objects.nonNull(globalOperationParameters)) {
            for (GlobalOperationParameter parameter : globalOperationParameters) {
                if (!docketNames.contains(parameter.getName())) {
                    resultOperationParameters.add(parameter);
                }
            }
        }

        resultOperationParameters.addAll(docketOperationParameters);
        return buildGlobalOperationParameters(resultOperationParameters);
    }
}
