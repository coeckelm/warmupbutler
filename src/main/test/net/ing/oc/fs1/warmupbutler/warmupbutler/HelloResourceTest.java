package net.ing.oc.fs1.warmupbutler.warmupbutler;

import net.ing.oc.fs1.warmupbutler.warmupbutler.resource.HelloResource;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloResourceTest {

    @Test
    public void testApp() {
        HelloResource helloResource = new HelloResource();
        String result = helloResource.hello();
        assertEquals( result, "Hello from Butler" );
    }

}
