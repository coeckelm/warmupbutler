package net.ing.oc.fs1.warmupbutler.warmupbutler.config;

import com.google.common.reflect.ClassPath;
import io.swagger.jaxrs.listing.ApiListingResource;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;

@Slf4j
public class JerseyResourceConfig extends ResourceConfig {

    /**
     * Environment property that determines whether swagger is enabled (default false)
     */
    public static final String ENVIRONMENT_PROPERTY_SWAGGER_ENABLED = "swagger.enabled";

    /**
     * Environment property that determines whether tracing is enabled (default true)
     */
    public static final String ENVIRONMENT_PROPERTY_TRACING_ENABLED = "tracing.enabled";

    /**
     * Environment property that determines whether analytics is enabled (default true)
     */
    public static final String ENVIRONMENT_PROPERTY_ANALYTICS_ENABLED = "analytics.enabled";

    private static final boolean DEFAULT_ENABLED = true;
    private static final boolean DEFAULT_DISABLED = false;

    public JerseyResourceConfig(final Environment environment,
                         final Consumer<ResourceConfig> mutator,
                         final Set<String> packagesToScan) {
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        configureJersey(packagesToScan);
        mutator.accept(this);
        configureSwagger(environment);
    }


    private boolean isDefaultDisabled(final Environment environment, final String propertyName) {
        return environment.getProperty(propertyName, Boolean.class, DEFAULT_DISABLED);
    }

    /**
     * Creates Swagger json/yaml for all Jersey endpoints (if enabled). Override this method to influence the behavior.
     */
    private void configureSwagger(final Environment environment) {
        if (isDefaultDisabled(environment, ENVIRONMENT_PROPERTY_SWAGGER_ENABLED)) {
            register(ApiListingResource.class);
        }
    }

    private void registerClassesUnderPackage(final ClassLoader classLoader, final String packageName) {

        // FixMe: Decide whether to keep this crazy stuff, only because we use spring-boot to package?
        // Do you know how to make better?
        // Apparently, the Jersey packages() method does not play nice with the Spring Boot jar bundler[1]
        // therefore we need to do this crazy stuff ourselves.
        // This should be fixed in Jersey, according to some links found by starting looking at [1] there is
        // a merge request [2] that should fix this.
        // [1]: https://github.com/spring-projects/spring-boot/issues/6598#issuecomment-238599756
        // [2]: https://github.com/jersey/jersey/pull/196

        log.info("Auto discovering packages for package [{}]", packageName);

        try {
            ClassPath.from(classLoader).getAllClasses().forEach(classInfo -> {
                        final String classNameWithoutSpringBootMagic =
                                classInfo.getName().replace("BOOT-INF.classes.", "");

                        if (classNameWithoutSpringBootMagic.startsWith(packageName)) {
                            log.info("Registering class {} as resource", classNameWithoutSpringBootMagic);
                            try {
                                register(Class.forName(classNameWithoutSpringBootMagic, true, getClassLoader()));
                            } catch (ClassNotFoundException e) {
                                log.error("This should not happen, we found a class that could not be found.", e);
                            }
                        } else {
                            log.trace("Skipping class {}", classInfo.getName());
                        }
                    }
            );
        } catch (IOException e) {
            log.error("Failed to auto discover", e);
        }
    }

    private void configureJersey(final Set<String> packagesToScan) {
        packagesToScan.forEach(packageName -> registerClassesUnderPackage(getClassLoader(), packageName));
    }
}
