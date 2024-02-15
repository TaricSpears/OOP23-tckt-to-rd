package it.unibo.turnandphase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;


public class FillRouteTest {

    @Test
    public void testFillRouteImpl() {
        // Test code goes here
        FillRouteImpl fillRouteImpl = new FillRouteImpl();
        assertEquals(true, fillRouteImpl.isRouteValid(null, null));
        
        
    }
}
