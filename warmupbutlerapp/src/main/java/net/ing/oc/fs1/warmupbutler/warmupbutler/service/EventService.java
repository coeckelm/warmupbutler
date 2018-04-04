package net.ing.oc.fs1.warmupbutler.warmupbutler.service;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import net.ing.oc.fs1.warmupbutler.warmupbutler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Event createEvent(Event event) {
        Event newEvent = eventRepository.save(event);
        return newEvent;
    }

    public Optional<Event> findEvent(String eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent;
    }

}
