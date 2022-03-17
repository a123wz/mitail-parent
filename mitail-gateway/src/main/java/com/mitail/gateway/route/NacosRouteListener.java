package com.mitail.gateway.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class NacosRouteListener implements ApplicationEventPublisherAware, Listener {

    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private DynamicRouteDefinitionRepository redisRouteDefinitionRepository;

    @Autowired
    RouteLocator routeLocator;

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String config) {
        log.info("路由配置:{}",config);
        List<RouteDefinition> routeDefinition  = JSON.parseObject(config,  new TypeReference<List<RouteDefinition>>(){});
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition.get(0))).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
//        routeLocator.getRoutes().subscribe(e->{
//            log.info("路由数据:{}",JSON.toJSONString(e));
//            List<GatewayFilter> filters = e.getFilters();
//            AsyncPredicate<ServerWebExchange> predicate = e.getPredicate();
//        });
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
