package net.ing.oc.fs1.warmupbutler.warmupbutler;

import net.ing.oc.fs1.warmupbutler.warmupbutler.resource.EventResource;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EventResourceTest {

    @Test
    public void testApp() {
        EventResource eventResource = new EventResource();
        String result = eventResource.getEvent();
        assertEquals( result, "Hello from Butler" );
    }

}
