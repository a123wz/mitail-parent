package com.mitail;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.mitail.weixin.mapper")
@EnableSwagger2
public class WeixinApplication {

    public static void main(String[] args) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("viv","");
        SpringApplication.run(WeixinApplication.class, args);
    }

}

