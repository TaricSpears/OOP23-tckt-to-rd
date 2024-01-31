package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.readercontroller.api.ReaderController;
import it.unibo.controller.readercontroller.impl.ReaderControllerImpl;
import it.unibo.model.player.api.Player;
import java.util.Set;
import java.util.LinkedHashSet;

public class MainControllerImpl implements MainController {

    private final Set<Player> players = new LinkedHashSet<>();
    private final ReaderController readerController = new ReaderControllerImpl();

    public void addNewPlayer(final Player player) {
        players.add(player);
    }

    public ReaderController getReaderController() {
        return this.readerController;
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    @Override
    public void disableRoute(int idRoute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disableRoute'");
    }

    @Override
    public void sendMessage(String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public void handleClaimRoute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleClaimRoute'");
    }

    @Override
    public void handleDrawCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleDrawCard'");
    }

}
