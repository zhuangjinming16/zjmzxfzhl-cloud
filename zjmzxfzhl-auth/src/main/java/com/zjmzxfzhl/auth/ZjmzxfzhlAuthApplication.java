package com.zjmzxfzhl.auth;

import com.zjmzxfzhl.common.remote.feign.annotation.EnableZjmzxfzhlFeignClients;
import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlAuthorizationServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 认证授权中心
 *
 * @author 庄金明
 */
@EnableZjmzxfzhlFeignClients
@EnableZjmzxfzhlAuthorizationServer
@SpringCloudApplication
public class ZjmzxfzhlAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlAuthApplication.class, args);
    }
}
