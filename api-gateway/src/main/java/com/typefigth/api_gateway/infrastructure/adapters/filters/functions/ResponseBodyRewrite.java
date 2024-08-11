package com.typefigth.api_gateway.infrastructure.adapters.filters.functions;

import com.typefigth.api_gateway.application.response.Response;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
public class ResponseBodyRewrite implements RewriteFunction<Object, Response> {

    @Override
    public Publisher<Response> apply(ServerWebExchange serverWebExchange, Object responseBody) {
        Response enCodedResponse = transformBody(serverWebExchange, responseBody);
        return Mono.just(enCodedResponse);
    }

    private Response transformBody(ServerWebExchange serverWebExchange, Object requestBody) {
        int statusCode = serverWebExchange.getResponse().getStatusCode() != null ? serverWebExchange.getResponse().getStatusCode().value() : 500;
        String path = serverWebExchange.getRequest().getPath().toString();
        String ip = serverWebExchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (ip == null) {
            ip = "unknown";
        }
        boolean isArray = requestBody instanceof List;
        String status = statusCode >= 400 ? "error" : "success";

        return Response.build(statusCode, path, statusCode < 400, requestBody, isArray, ip, status);
    }

}
