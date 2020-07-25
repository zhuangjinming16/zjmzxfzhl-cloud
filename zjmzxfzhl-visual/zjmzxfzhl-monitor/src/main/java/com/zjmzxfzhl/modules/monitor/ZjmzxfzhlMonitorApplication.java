package com.zjmzxfzhl.modules.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 监控中心
 *
 * @author 庄金明
 */
@EnableAdminServer
@SpringCloudApplication
public class ZjmzxfzhlMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjmzxfzhlMonitorApplication.class, args);
    }
}
