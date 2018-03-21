package net.ing.oc.fs1.warmupbutler.warmupbutler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix = "warmupbutler", ignoreUnknownFields = false)
public class ApplicationProperties {

    @Valid
    private Redis redis = new Redis();

    public static class Redis {

        @NotNull
        private String host;

        @NotNull
        private String password;

        @NotNull
        private Integer port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

    public Redis getRedis() {
        return redis;
    }
}
