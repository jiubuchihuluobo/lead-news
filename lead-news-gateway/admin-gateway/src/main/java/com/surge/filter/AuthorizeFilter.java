package com.surge.filter;

import com.alibaba.fastjson2.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthorizeFilter implements GlobalFilter {

    private final static List<String> urlList = new ArrayList<>();

    static {
        urlList.add("/login/in");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestUrl = request.getURI().getPath();
        for (String url : urlList) {
            if (requestUrl.contains(url)) {
                return chain.filter(exchange);
            }
        }
        return null;
    }

    private Mono<Void> writeMessage(ServerWebExchange exchange, String message) {
        Map<String, Object> map = Map.of("code", HttpStatus.UNAUTHORIZED.value(), "errorMessage", message);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(map));
        return response.writeWith(Flux.just(buffer));
    }

}
