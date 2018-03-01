package net.ing.oc.fs1.warmupbutler.warmupbutler.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private String eventId;
    private String eventName;
    @Future
    private Date eventDate;


    public Event(@JsonProperty("eventId") String eventId, @JsonProperty("eventName") String eventName, @JsonProperty("eventDate") @Future Date eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public String getEventId() {
        return eventId;
    }

    public String getDescription(){
        return "eventId: " + this.eventId + " | eventName: " + this.eventName + " | eventDate: " + this.eventDate;
    }
}
