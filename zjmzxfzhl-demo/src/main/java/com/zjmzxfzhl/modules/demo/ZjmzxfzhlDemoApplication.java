package com.zjmzxfzhl.modules.demo;

import com.zjmzxfzhl.common.remote.feign.annotation.EnableZjmzxfzhlFeignClients;
import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Demo模块
 *
 * @author 庄金明
 */
@EnableZjmzxfzhlResourceServer
@EnableZjmzxfzhlFeignClients
@SpringCloudApplication
public class ZjmzxfzhlDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlDemoApplication.class, args);
    }
}
