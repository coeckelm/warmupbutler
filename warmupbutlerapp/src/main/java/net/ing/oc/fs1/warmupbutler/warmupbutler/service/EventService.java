package net.ing.oc.fs1.warmupbutler.warmupbutler.service;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;

public interface EventService {

    Event createEvent(Event event);

    Event findEvent(String eventId);

    Event modifyEvent(Event event);
}
