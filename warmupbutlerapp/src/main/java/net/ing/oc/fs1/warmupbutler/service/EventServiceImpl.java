package net.ing.oc.fs1.warmupbutler.service;

import net.ing.oc.fs1.warmupbutler.domain.Event;
import net.ing.oc.fs1.warmupbutler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        if (event.getId() == null) {
            event.setId(ThreadLocalRandom.current().nextLong(100)); // workaround.
        }
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.orElseThrow(() -> new EventNotFoundException("Event with ID = '" + eventId + "' not found."));
    }

    @Override
    public Event modifyEvent(Event event) {
        // Check if the event is present in the db.
        getEvent(event.getId());
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Optional.ofNullable(events).orElse(Collections.emptyList());
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.delete(getEvent(eventId));
    }
}
