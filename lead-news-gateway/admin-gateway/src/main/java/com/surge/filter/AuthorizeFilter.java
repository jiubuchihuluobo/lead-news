package com.surge.filter;

import com.alibaba.fastjson2.JSON;
import com.surge.util.common.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        String requestUrl = request.getURI().getPath();
        for (String url : AuthorizeFilter.urlList) {
            if (requestUrl.contains(url)) {
                return chain.filter(exchange);
            }
        }

        try {
            String token = request.getHeaders().getFirst("token");
            if (StringUtils.isBlank(token)) {
                return this.writeMessage(exchange, "认证失败");
            }
            Claims claims = JwtUtil.getClaims(token);
            if (JwtUtil.verifyToken(claims) > 0) {
                return this.writeMessage(exchange, "认证过期");
            }
            Integer id = claims.get("id", Integer.class);
            log.info("token校验成功id:{}\tURL:{}", id, request.getURI().getPath());
            request.mutate().header("userId", String.valueOf(id));
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("token校验失败:{}", e.toString());
            return this.writeMessage(exchange, "认证失败");
        }

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
