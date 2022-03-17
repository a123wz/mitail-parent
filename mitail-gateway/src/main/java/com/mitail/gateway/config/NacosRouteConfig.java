package com.mitail.gateway.config;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.client.config.NacosConfigService;
import com.mitail.gateway.route.NacosRouteListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
@Slf4j
public class NacosRouteConfig {

    private ConfigService configService;

    @Autowired
    private NacosRouteListener routeDefinitionUtils;

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String nacosServerAddress;

    @Bean
    public ConfigService nacosConfigService(){
        log.info("nacos Config Service init");
        return configService;
    }

    @PostConstruct
    public void init(){
        Properties properties = new Properties();
        try {
            log.info("nacos config address:{}",nacosServerAddress);
            properties.put("serverAddr", nacosServerAddress);
            configService = new NacosConfigService(properties);
            configService.addListener("gateway-route", "DEFAULT_GROUP", routeDefinitionUtils);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
