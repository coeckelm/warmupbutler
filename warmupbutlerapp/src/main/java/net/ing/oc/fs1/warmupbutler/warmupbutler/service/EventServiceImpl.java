package net.ing.oc.fs1.warmupbutler.warmupbutler.service;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import net.ing.oc.fs1.warmupbutler.warmupbutler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findEvent(String eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.orElseThrow(() -> new EventNotFoundException("Event with ID = '" + eventId + "' found."));
    }

    @Override
    public Event modifyEvent(Event event) {
        // Just used to check if the event passed is present in the db.
        findEvent(event.getEventId());
        return eventRepository.save(event);
    }
}
