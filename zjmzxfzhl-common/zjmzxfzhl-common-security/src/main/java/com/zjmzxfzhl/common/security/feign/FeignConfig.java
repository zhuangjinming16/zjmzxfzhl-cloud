package com.zjmzxfzhl.common.security.feign;

import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Feign配置
 *
 * @author 庄金明
 **/
@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = requestTemplate -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails dateils = (OAuth2AuthenticationDetails) authentication.getDetails();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s",
                        SecurityConstants.BEARER_TOKEN_TYPE, dateils.getTokenValue()));
            }
        };
        return requestInterceptor;
    }
}
