package it.unibo.turnandphase;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.impl.MainControllerImpl;
import it.unibo.controller.phasecontroller.api.PhaseController;
import it.unibo.model.city.impl.CityImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;
import java.util.List;
import java.awt.Color;

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
        GamePrep gamePrep = new GamePrep();
        Player player = new PlayerImpl(null, null, 0);
        Region region = new Region(0, 0, 0, 0, 0, 0, null, null);
        Route route = new RouteImpl(new EdgeData(new CityImpl(null), new CityImpl(null), 5), null, 0, null);
        gamePrep.prepGame(List.of(new Pair<String, Color>(player.getName(), player.getColor())), List.of(route));
        MainController controller = new MainControllerImpl();
        controller.startGame();
        PhaseController phase = controller.getPhaseController();
        FillRouteImpl fillRouteImpl = new FillRouteImpl();
        assertEquals(false, fillRouteImpl.isRouteValid());

    }
}
