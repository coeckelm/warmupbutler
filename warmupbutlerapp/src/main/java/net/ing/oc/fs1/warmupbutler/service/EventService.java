package net.ing.oc.fs1.warmupbutler.service;

import net.ing.oc.fs1.warmupbutler.domain.Event;

import java.util.List;

public interface EventService {

    Event createEvent(Event event);

    Event getEvent(Long eventId);

    Event modifyEvent(Event event);

    List<Event> getAllEvents();

    void deleteEvent(Long eventId);
}
