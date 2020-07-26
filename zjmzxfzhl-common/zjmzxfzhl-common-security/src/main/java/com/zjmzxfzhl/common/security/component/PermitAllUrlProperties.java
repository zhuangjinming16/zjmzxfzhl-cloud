package com.zjmzxfzhl.common.security.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄金明
 * @date
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class PermitAllUrlProperties {

    @Getter
    @Setter
    private List<String> urls = new ArrayList<>();
}
