package com.zjmzxfzhl.gateway.config;

import com.zjmzxfzhl.gateway.handler.KaptchaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * 路由配置信息
 *
 * @author 庄金明
 */
@Configuration
public class RouterFunctionConfiguration {
    @Autowired
    private KaptchaHandler kaptchaHandler;

    @SuppressWarnings("rawtypes")
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/captcha.jpg").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), kaptchaHandler);
    }
}
