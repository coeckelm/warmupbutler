package net.ing.oc.fs1.warmupbutler.warmupbutler.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


public class Event implements Serializable {
    private String eventId;
    private String eventName;


    @JsonCreator
    public Event(@JsonProperty("eventId") String eventId, @JsonProperty("eventName") String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
