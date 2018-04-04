package net.ing.oc.fs1.warmupbutler.warmupbutler.resource;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import net.ing.oc.fs1.warmupbutler.warmupbutler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/events")
public class EventResource {

    @Autowired
    private EventService eventService;

    @GET
    @Path("/{eventId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEvent(@PathParam("eventId") String eventId) {
        return "Event found " + eventService.findEvent(eventId).getDescription();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String createEvent(@Valid Event event) {
        eventService.createEvent(event);
        return "Event created: " + event.getDescription();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public String modifyEvent(@Valid Event event) {
        eventService.modifyEvent(event);
        return "Event created: " + event.getDescription();
    }

}