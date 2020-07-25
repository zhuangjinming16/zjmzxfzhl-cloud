package com.zjmzxfzhl.modules.app;

import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlFeignClients;
import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * APP demo模块
 *
 * @author 庄金明
 */
@EnableZjmzxfzhlResourceServer
@EnableZjmzxfzhlFeignClients
@SpringCloudApplication
public class ZjmzxfzhlAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlAppApplication.class, args);
    }
}
