package com.xueqing.demo.springdubbo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @ClassName: Swagger2Config
 * @Description: Swagger2配置
 * @author XueQing Wang
 * @date 2018年7月11日 15:37:36
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 日志管理
     */
    private Logger log = LoggerFactory.getLogger(Swagger2Config.class);

    /**
     * @description:
     * @author cheng
     * @dateTime 2018/5/10 14:13
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .pathMapping("/api/v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xueqing.demo.springdubbo"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @description:
     * @author cheng
     * @dateTime 2018/5/10 14:13
     */
    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("博客地址:https://blog.csdn.net/wangxueqing52")
                .contact(new Contact("XueQing Wang", "https://blog.csdn.net/wangxueqing52", "1017513901@qq.com"))
                .version("1.0")
                .build();
    }

}
