package com.typefigth.api_gateway.infrastructure.adapters.filters;

import com.typefigth.api_gateway.application.response.Response;
import com.typefigth.api_gateway.infrastructure.adapters.filters.functions.ResponseBodyRewrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class ResponseBodyRewriteFilter implements GlobalFilter, Ordered {

    private final ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFactory;
    private final ResponseBodyRewrite responseBodyRewrite;

    public ResponseBodyRewriteFilter(ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFactory, ResponseBodyRewrite responseBodyRewrite) {
        this.modifyResponseBodyFactory = modifyResponseBodyFactory;
        this.responseBodyRewrite = responseBodyRewrite;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return modifyResponseBodyFactory
                .apply(new ModifyResponseBodyGatewayFilterFactory.Config()
                        .setRewriteFunction(Object.class, Response.class, responseBodyRewrite))
                .filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}
