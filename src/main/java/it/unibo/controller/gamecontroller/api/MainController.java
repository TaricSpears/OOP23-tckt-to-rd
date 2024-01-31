package it.unibo.controller.gamecontroller.api;

import it.unibo.controller.readercontroller.api.ReaderController;
import it.unibo.model.player.api.Player;

public interface MainController {

    void addNewPlayer(final Player player);

    ReaderController getReaderController();

    void endTurn();

    void disableRoute(int idRoute);

    void sendMessage(String message);

    void handleClaimRoute();

    void handleDrawCard();
}
