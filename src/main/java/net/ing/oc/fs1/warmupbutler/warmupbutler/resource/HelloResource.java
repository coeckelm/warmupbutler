package net.ing.oc.fs1.warmupbutler.warmupbutler.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Component

public class HelloResource {
    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String hello() {
        return "Hello from Butler";


    }
}



