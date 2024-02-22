package it.unibo.turnandphase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.turnmanager.api.TurnManager;
import it.unibo.model.turnmanager.impl.TurnManagerImpl;
import it.unibo.model.phasemanager.api.PhaseManager;
import it.unibo.model.phasemanager.impl.PhaseManagerImpl;

/*
 * Test for the turn and phase management.
 */
class TestTurnAndPhase {

    @Test
    void testTurnManager() {

        final List<PlayerImpl> players = new ArrayList<>();
        final int carriageNum = 45;
        players.add(new PlayerImpl("Player 1", carriageNum));
        players.add(new PlayerImpl("Player 2", carriageNum));
        players.add(new PlayerImpl("Player 3", carriageNum));
        players.add(new PlayerImpl("Player 4", carriageNum));

        final TurnManager turnManager = new TurnManagerImpl(players);
        final List<Player> players2 = new ArrayList<>(turnManager.getPlayers());

        assertEquals(1, turnManager.getCurrentTurn());
        assertEquals(players2.get(0), turnManager.getCurrentPlayer());

        turnManager.switchToNextPlayer();
        assertEquals(1, turnManager.getCurrentTurn());
        assertEquals(players2.get(1), turnManager.getCurrentPlayer());

        turnManager.switchToNextPlayer();
        assertEquals(1, turnManager.getCurrentTurn());
        assertEquals(players2.get(2), turnManager.getCurrentPlayer());

        turnManager.switchToNextPlayer();
        assertEquals(1, turnManager.getCurrentTurn());
        assertEquals(players2.get(3), turnManager.getCurrentPlayer());

        turnManager.switchToNextPlayer();
        assertEquals(2, turnManager.getCurrentTurn());
        assertEquals(players2.get(0), turnManager.getCurrentPlayer());

    }

    @Test
    void testPhaseManager() {

        PhaseManagerImpl phaseManager = new PhaseManagerImpl();

        assertEquals(PhaseManager.Phase.START, phaseManager.getCurrentPhase());

        phaseManager.switchPhase();
        assertEquals(PhaseManager.Phase.MID, phaseManager.getCurrentPhase());

        phaseManager.switchPhase();
        assertEquals(PhaseManager.Phase.END, phaseManager.getCurrentPhase());

        phaseManager = new PhaseManagerImpl();
        assertEquals(PhaseManager.Phase.START, phaseManager.getCurrentPhase());

    }
}
