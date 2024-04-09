package com.surge.filter;

import com.alibaba.fastjson2.JSON;
import com.surge.util.common.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
@Order(0)
@Slf4j
public class AuthorizeFilter implements GlobalFilter {

    private final static List<String> urlList = new ArrayList<>();

    static {
        AuthorizeFilter.urlList.add("/login/in");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        for (String requestUrl : AuthorizeFilter.urlList) {
            if (url.contains(requestUrl)) {
                return chain.filter(exchange);
            }
        }
        try {
            Jws<Claims> jws = JwtUtil.verifyToken(request.getHeaders().getFirst("token"));
        } catch (Exception e) {
            log.error("token校验失败:{}", e.toString());
            return this.writeMessage(exchange, e.toString());
        }
        return chain.filter(exchange);
    }

    private Mono<Void> writeMessage(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(Map.of("code", HttpStatus.UNAUTHORIZED.value(), "errorMessage", message)));
        return response.writeWith(Flux.just(buffer));
    }

}
