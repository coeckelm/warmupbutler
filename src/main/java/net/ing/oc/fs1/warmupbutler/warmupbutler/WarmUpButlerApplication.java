/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.ing.oc.fs1.warmupbutler.warmupbutler;

import lombok.extern.slf4j.Slf4j;
import net.ing.oc.fs1.warmupbutler.warmupbutler.config.JerseyResourceConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@SpringBootApplication
@ComponentScan("net.ing.oc.fs1.warmupbutler.warmupbutler")
@Slf4j
public class WarmUpButlerApplication {
    private final String defaultBaseResourcePackagePath = getClass().getPackage().getName() + ".resource";

    @Autowired
    private Environment environment;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WarmUpButlerApplication.class, args);
    }

    /**
     * Take care not to use packages() scanning on this level because it doesn't work nice [1][2]
     * with the currently used Spring Boot!
     * [1]: https://github.com/spring-projects/spring-boot/issues/6598#issuecomment-238599756
     * [2]: https://github.com/jersey/jersey/pull/196
     *
     * @return
     */
    protected Consumer<ResourceConfig> customJerseyConfig() {
        return resourceConfig -> {
        };
    }


    /**
     * @param jerseyConfigMutators The mutator beans. Using List here to inject all mutators, no order is implied.
     * @return
     */
    @Bean
    @Lazy(false)
    public ResourceConfig jerseyResourceConfig(Optional<List<Consumer<ResourceConfig>>> jerseyConfigMutators) {
        final Consumer<ResourceConfig> combinedMutator =
                resourceConfig -> {
                    // side-effects are acceptable here because ResourceConfig itself is mutable
                    jerseyConfigMutators.ifPresent(mutators ->
                            mutators.forEach(mutator -> mutator.accept(resourceConfig))
                    );
                    customJerseyConfig().accept(resourceConfig);
                };

        return new JerseyResourceConfig(environment, combinedMutator, packagesToScan());
    }

    /**
     * Set of package names that shall be scanned to register with Jersey. Defaults to:
     * <p>
     * <code>
     * Set(  getClass().getPackage().getName() + ".resource"  )
     * </code>
     *
     * @return Set of application-level packages to scan
     */
    protected Set<String> packagesToScan() {
        return new HashSet<>(Collections.singletonList(baseResourcePackagePath()));
    }

    /**
     * The default path Merak looks for its Jersey endpoints. Override you use a different location.
     */
    protected String baseResourcePackagePath() {
        return defaultBaseResourcePackagePath;
    }

}
