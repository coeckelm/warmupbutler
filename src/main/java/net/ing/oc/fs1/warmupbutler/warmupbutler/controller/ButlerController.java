package net.ing.oc.fs1.warmupbutler.warmupbutler.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.glassfish.jersey.server.ManagedAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@Api(tags = "butler")
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ButlerController {
    @GET
    @Path("/hello")
    @ApiOperation(value = "Return a toggles'participation", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Successful return a hello"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Internal server error")}
    )
    public String hello(@Suspended final AsyncResponse asyncResponse) {
        return "Hello from Butler";


    }
}



