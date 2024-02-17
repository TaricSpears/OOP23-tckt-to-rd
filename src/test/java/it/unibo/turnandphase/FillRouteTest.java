package it.unibo.turnandphase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import it.unibo.commons.EdgeData;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;

public class FillRouteTest {

    /**
     * 
     */
    @Test
    public void testFillRouteEmpty() {
        // Test code goes here
        EdgeData edgeData = new EdgeData(null, null, 5);
        Player player = new PlayerImpl(null, null, 0);
        Route route = new RouteImpl(edgeData, Color.GRAY, 5, null);
        FillRouteImpl fillRouteImpl = new FillRouteImpl(player, route);
        assertEquals(false, fillRouteImpl.isRouteValid());

    }
}
