package com.zjmzxfzhl.auth;

import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 认证授权中心
 *
 * @author 庄金明
 */
@EnableZjmzxfzhlFeignClients
@SpringCloudApplication
public class ZjmzxfzhlAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlAuthApplication.class, args);
    }
}
