package com.mitail.gateway.route;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DynamicRouteDefinitionRepository implements RouteDefinitionRepository {

    private Map<String,RouteDefinition> routes = new ConcurrentHashMap<>();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> gatewayRouteEntityList = new ArrayList<>(routes.values());
        return Flux.fromIterable(gatewayRouteEntityList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
         return route.flatMap(routeDefinition -> {
             routes.put(routeDefinition.getId(),routeDefinition);
             return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
