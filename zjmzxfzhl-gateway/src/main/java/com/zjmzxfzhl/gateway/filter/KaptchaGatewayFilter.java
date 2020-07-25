package com.zjmzxfzhl.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjmzxfzhl.common.core.constant.Constants;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import com.zjmzxfzhl.common.core.exception.BaseException;
import com.zjmzxfzhl.common.core.redis.util.RedisUtil;
import com.zjmzxfzhl.common.core.util.CommonUtil;
import com.zjmzxfzhl.common.core.util.WebUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author 庄金明
 * @date
 */
@Slf4j
@Component
public class KaptchaGatewayFilter extends AbstractGatewayFilterFactory {
    @Value("${captchaFilter.clients}")
    private List<String> clients;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }

            // 刷新token，直接向下执行
            String grantType = request.getQueryParams().getFirst("grant_type");
            if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
                return chain.filter(exchange);
            }

            // 配置了需要验证验证码的才进行验证， 直接向下执行
            try {
                String[] clientInfos = WebUtils.getClientId(request);
                if (!clients.contains(clientInfos[0])) {
                    return chain.filter(exchange);
                }
                // 校验验证码
                checkCode(request);
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                final String errMsg = e.getMessage();
                return response.writeWith(Mono.create(monoSink -> {
                    try {
                        byte[] bytes = objectMapper.writeValueAsBytes(Result.error(errMsg));
                        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
                        monoSink.success(dataBuffer);
                    } catch (JsonProcessingException jsonProcessingException) {
                        log.error("对象输出异常", jsonProcessingException);
                        monoSink.error(jsonProcessingException);
                    }
                }));
            }

            return chain.filter(exchange);
        };
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(ServerHttpRequest request) {
        String uuid = request.getQueryParams().getFirst("uuid");
        String captcha = request.getQueryParams().getFirst("captcha");
        CommonUtil.isEmptyStr(uuid, "验证码uuid不能为空");
        CommonUtil.isEmptyStr(captcha, "验证码不能为空");

        String cacheCaptcha = (String) redisUtil.get(Constants.PREFIX_USER_CAPTCHA + uuid);
        if (!captcha.equals(cacheCaptcha)) {
            throw new BaseException("验证码错误或已失效");
        }
        // 验证码验证通过后，应立即删除缓存，防止恶意暴力破解密码
        redisUtil.del(Constants.PREFIX_USER_CAPTCHA + uuid);
    }

}
