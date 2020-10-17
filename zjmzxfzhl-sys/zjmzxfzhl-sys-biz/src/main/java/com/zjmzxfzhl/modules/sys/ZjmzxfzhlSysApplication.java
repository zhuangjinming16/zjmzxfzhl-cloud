package com.zjmzxfzhl.modules.sys;

import com.zjmzxfzhl.common.remote.feign.annotation.EnableZjmzxfzhlFeignClients;
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
public class ZjmzxfzhlSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlSysApplication.class, args);
    }
}
