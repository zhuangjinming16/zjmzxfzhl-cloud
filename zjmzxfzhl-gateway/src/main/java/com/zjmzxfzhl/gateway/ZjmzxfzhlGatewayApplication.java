package com.zjmzxfzhl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动程序
 *
 * @author 庄金明
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZjmzxfzhlGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlGatewayApplication.class, args);
    }
}