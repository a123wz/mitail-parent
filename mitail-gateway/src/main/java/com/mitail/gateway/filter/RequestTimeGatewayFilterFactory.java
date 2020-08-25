package com.mitail.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;

@Component
@Slf4j
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    private static final String KEY = "withParams";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String flow_no = "bs-tes";
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("flow_no", flow_no)
                    .build();
            Thread.currentThread().setName(flow_no);
            log.info("flow_no:{},{},{}",flow_no,exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_HANDLER_MAPPER_ATTR)
            ,exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR));
            log.info("flow_no1:{},{},{}",flow_no,exchange.getAttributes().get(ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR)
                    ,exchange.getAttributes().get(ServerWebExchangeUtils.CLIENT_RESPONSE_HEADER_NAMES));

            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
            return chain.filter(exchange.mutate().request(request).build()).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        Thread.currentThread().setName(flow_no);
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append("ms");
                            if (config.isWithParams()) {
                                sb.append(" params:").append(exchange.getRequest().getQueryParams());
                            }
                            log.info(sb.toString());
                        }
                    })
            );
        };
    }


    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }

    }
}
