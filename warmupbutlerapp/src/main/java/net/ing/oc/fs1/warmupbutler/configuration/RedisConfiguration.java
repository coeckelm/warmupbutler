package net.ing.oc.fs1.warmupbutler.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(applicationProperties.getRedis().getHost());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(applicationProperties.getRedis().getPassword()));
        redisStandaloneConfiguration.setPort(applicationProperties.getRedis().getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

}
