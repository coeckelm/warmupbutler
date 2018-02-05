package net.ing.oc.fs1.warmupbutler.warmupbutler.config;

import net.ing.oc.fs1.warmupbutler.warmupbutler.resource.HelloResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloResource.class);
    }
}

