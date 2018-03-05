package net.ing.oc.fs1.warmupbutler.warmupbutler.resource;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import org.springframework.data.redis.core.ValueOperations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response hello() {
        return Response.ok().entity("Hello!").build();
    }

    @GET
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEvent(@PathParam("name") String name) {
        return Response.ok().entity("Welcome " + name).build();
    }

}
