package com.xsy.gateway.filter.factory;

import com.xsy.constants.RedisPrefix;
import org.apache.commons.configuration.ConfigurationMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> {
    @Autowired
    private RedisTemplate redisTemplate;
    public TokenGatewayFilterFactory(){
        super(Config.class);

    }
    //Config 参数就是基于当前类中Config对象
    @Override
    public GatewayFilter apply(Config config) {

        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                //判断是否需要token

                boolean flag=config.requiredToken;
                if (flag){
                    //获取token信息
                    if (exchange.getRequest().getQueryParams().get("token")==null) throw new RuntimeException("非法访问!");
                    String token = exchange.getRequest().getQueryParams().get("token").get(0);
                    //判断token与redis中的是否正确
                    if (!redisTemplate.hasKey(RedisPrefix.TOKEN_KEY+token)) throw new RuntimeException("非法访问!");
                }
                return chain.filter(exchange);
            }
        };
    }

    @Override
    //用来配置将使用filter时指定值赋值给Config中的哪个属性
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("requiredToken");
    }

    public static class Config{
        private boolean requiredToken;

        public boolean isRequiredToken() {
            return requiredToken;
        }

        public void setRequiredToken(boolean requiredToken) {
            this.requiredToken = requiredToken;
        }
    }


}
