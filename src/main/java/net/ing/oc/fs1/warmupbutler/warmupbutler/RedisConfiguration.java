package net.ing.oc.fs1.warmupbutler.warmupbutler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.pw}")
    private String password;
    @Value("${redis.user}")
    private String user;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        System.out.println("setting up port: " + this.port);
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(this.host);
        jedisConFactory.setClientName(this.user);
        jedisConFactory.setPort(this.port);
        jedisConFactory.setPassword(this.password);
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}
