package net.ing.oc.fs1.warmupbutler.warmupbutler.resource;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Component

public class EventResource {
    @Autowired
    private RedisTemplate redisTemplate;

    @GET
    @Path("/event/{eventId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEvent(@PathParam("eventId") String eventId) {
        ValueOperations values = redisTemplate.opsForValue();
        @Nullable Event event = (Event) values.get(eventId);

        return "Event found " + event.getEventName();
    }


    @POST
    @Path("/event")
    @Produces({MediaType.APPLICATION_JSON})
    public String createEvent(Event event) {
        ValueOperations values = redisTemplate.opsForValue();
        values.set(event.getEventId(), event);

        return "Event created: " + event.getEventId();
    }



}



