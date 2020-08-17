package com.zjmzxfzhl.common.security.feign;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * Feign配置
 *
 * @author 庄金明
 **/
@Configuration
public class FeignConfig {
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = requestTemplate -> {
            Collection<String> fromHeader = requestTemplate.headers().get(SecurityConstants.INNER);
            if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.INNER_TRUE)) {
                String clientInfo = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
                String secret = Base64.encode(clientInfo.getBytes(StandardCharsets.UTF_8));
                requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s",
                        SecurityConstants.X_ZJMZXFZHL_APPLICATION_TOKEN_TYPE, secret));
            } else {
                SecurityContext securityContext = SecurityContextHolder.getContext();
                Authentication authentication = securityContext.getAuthentication();
                if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                    OAuth2AuthenticationDetails dateils = (OAuth2AuthenticationDetails) authentication.getDetails();
                    requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s",
                            SecurityConstants.BEARER_TOKEN_TYPE, dateils.getTokenValue()));
                }
            }
        };
        return requestInterceptor;
    }
}
