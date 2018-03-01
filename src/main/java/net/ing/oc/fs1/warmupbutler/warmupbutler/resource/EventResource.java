package net.ing.oc.fs1.warmupbutler.warmupbutler.resource;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

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
        Event event = (Event) values.get(eventId);
        Optional<Event> optionalEvent = Optional.ofNullable(event);
        if(optionalEvent.isPresent()) {
            return "Event found " + optionalEvent.get().getEventName();
        }
        return "Event not found";
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



