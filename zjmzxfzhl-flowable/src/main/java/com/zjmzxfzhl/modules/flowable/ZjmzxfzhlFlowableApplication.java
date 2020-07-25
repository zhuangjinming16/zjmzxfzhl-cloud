package com.zjmzxfzhl.modules.flowable;

import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlFeignClients;
import com.zjmzxfzhl.common.security.annotation.EnableZjmzxfzhlResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 系统模块
 *
 * @author 庄金明
 */
@EnableZjmzxfzhlResourceServer
@EnableZjmzxfzhlFeignClients
@SpringCloudApplication
public class ZjmzxfzhlFlowableApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlFlowableApplication.class, args);
    }
}
