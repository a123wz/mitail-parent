package com.mitail;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.client.*;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);
    }

    /*@Bean
    public WebSocketClient getWebSocketClient(){
//        return new JettyWebSocketClient();
//        return new ReactorNettyWebSocketClient();
//        return new UndertowWebSocketClient();
//        return new TomcatWebSocketClient();
//        return null;
        *//*return new WebSocketClient(){
            @Override
            public Mono<Void> execute(URI uri, WebSocketHandler webSocketHandler) {
                log.info("1111111111111");
                return null;
            }

            @Override
            public Mono<Void> execute(URI uri, HttpHeaders httpHeaders, WebSocketHandler webSocketHandler) {
                log.info("222222222");
                return null;
            }
        };*//*
    }*/

    /*@Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String httpUri = "http://httpbin.org:80";
        return builder.routes()
            .route(p -> p
                .path("/get")
                .filters(f -> f.addRequestHeader("Hello", "World"))
                .uri(httpUri))
            .route(p -> p
                .host("*.hystrix.com")
                .filters(f -> f
                    .hystrix(config -> config
                        .setName("mycmd")
                        .setFallbackUri("forward:/fallback")))
                .uri(httpUri))
            .build();
    }*/
}
