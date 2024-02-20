package it.unibo.turnandphase;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.impl.MainControllerImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;

/**
 * This class is used to test the FillRoute class.
 * 
 */

// CHECKSTYLE: MagicNumber OFF
public class FillRouteTest {
    /**
     * This method is used to test a fillRoute empty object.
     */
    @Test
    public void testFillRouteEmpty() {
        // Test code goes here
        MainController controller = new MainControllerImpl();
        Player player = new PlayerImpl(null, null, 0);
        Region region = new Region(0, 0, 0, 0, 0, 0, null, null);
        FillRouteImpl fillRouteImpl = new FillRouteImpl(player, region, controller);
        assertEquals(false, fillRouteImpl.isRouteValid());

    }
}
