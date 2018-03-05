package net.ing.oc.fs1.warmupbutler.warmupbutler;

import net.ing.oc.fs1.warmupbutler.warmupbutler.model.Event;
import net.ing.oc.fs1.warmupbutler.warmupbutler.resource.EventResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventResourceTest {
    @InjectMocks
    private EventResource sut;
    @Mock
    private RedisTemplate redisTemplate;

    @Test
    public void getEventTest_happyFlow() {
        Event event = new Event("1", "first event", null);
        ValueOperations valueOperations = Mockito.mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(any())).thenReturn(event);
        String result = sut.getEvent("1");
        assertEquals( "Event found eventId: 1 | eventName: first event | eventDate: null" , result);
    }




}
