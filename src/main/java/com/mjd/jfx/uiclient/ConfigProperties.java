package com.mjd.jfx.uiclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Configuration
//@ConfigurationProperties
//@PropertySource("classpath:application.yaml")
@Component
public class ConfigProperties {

    @Value("${weather.host}")
    private String hostPath;

    @Value("${weather.apiId}")
    private String appId;

    public String getHostPath() {
        return hostPath;
    }

    public String getAppId() {
        return appId;
    }
}
