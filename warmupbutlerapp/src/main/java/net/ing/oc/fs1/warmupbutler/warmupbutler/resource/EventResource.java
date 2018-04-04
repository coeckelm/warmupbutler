package net.ing.oc.fs1.warmupbutler.warmupbutler.resource;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import net.ing.oc.fs1.warmupbutler.warmupbutler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/events")
public class EventResource {

    @Autowired
    private EventService eventService;

    @GET
    @Path("/{eventId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEvent(@PathParam("eventId") String eventId) {
        Optional<Event> optionalEvent = eventService.findEvent(eventId);
        if (optionalEvent.isPresent()) {
            return "Event found " + optionalEvent.get().getDescription();
        }
        return "Event not found";
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String createEvent(@Valid Event event) {
        eventService.createEvent(event);
        return "Event created: " + event.getDescription();
    }

}