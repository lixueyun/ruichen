package com.ruichen.restful.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName  SwaggerConfig
 * @Description swagger配置类
 * @author  lixueyun
 * @Date  2019/4/4 17:20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    @Value("${swagger.base-package}")
    private String basePackage;

    /**
     * @methodName  createRestApi
     * @description 配置
     * @param
     * @author  lixueyun
     * @Date  2019/4/4 17:57
     * @return  springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @methodName  apiInfo
     * @description api详情
     * @param
     * @author  lixueyun
     * @Date  2019/4/4 17:57
     * @return  springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("睿宸DOC")
                .description("睿宸 Api文档")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }

}
