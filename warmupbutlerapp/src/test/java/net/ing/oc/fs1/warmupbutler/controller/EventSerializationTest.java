package net.ing.oc.fs1.warmupbutler.controller;

import net.ing.oc.fs1.warmupbutler.DataGenerator;
import net.ing.oc.fs1.warmupbutler.domain.Event;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@JsonTest
public class EventSerializationTest {

    private static final String TEMPLATE = "/json/expected-event.json";

    @Autowired
    private JacksonTester<Event> jacksonTester;

    @Test
    public void checkSerialization() throws Exception {
        Event event = DataGenerator.createEvent();
        JsonContent<Event> content  = jacksonTester.write(event);
        System.out.println(content.getJson());
        // Assert against a `.json` file.
        Assertions.assertThat(content).isEqualToJson(TEMPLATE);
    }

    @Test
    public void checkDeserialization() throws Exception {
        Event expectedEvent = DataGenerator.createEvent();
        ObjectContent<Event> content  = jacksonTester.read(TEMPLATE);
        Event event = content.getObject();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(event.getId()).isEqualTo(expectedEvent.getId());
            softly.assertThat(event.getDescription()).isEqualTo(expectedEvent.getDescription());
            softly.assertThat(event.getName()).isEqualTo(expectedEvent.getName());
            softly.assertThat(event.getStartDateTime()).isEqualTo(expectedEvent.getStartDateTime());
            softly.assertThat(event.getLocation()).isEqualTo(expectedEvent.getLocation());
        });
    }
}
