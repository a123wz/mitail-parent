package com.mitail.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
        prefix = "swagger"
)
@Data
public class SwaggerProperties {

    private String title;

    private String pack;

    private String description;

    private String version = "1.0";
}
