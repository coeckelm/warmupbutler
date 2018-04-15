package net.ing.oc.fs1.warmupbutler;

import net.ing.oc.fs1.warmupbutler.domain.Event;

import java.time.LocalDateTime;
import java.time.Month;

public final class DataGenerator {

    private DataGenerator() {}

    public static Event createEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Birthday party");
        event.setLocation("Brussels");
        event.setDescription("Food and beverages");
        event.setStartDateTime(LocalDateTime.of(2018, Month.APRIL, 10, 22, 0));
        return event;
    }
}
