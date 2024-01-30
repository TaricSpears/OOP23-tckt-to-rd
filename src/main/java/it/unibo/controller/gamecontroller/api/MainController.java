package it.unibo.controller.gamecontroller.api;

public interface MainController {

    void endTurn();

    void disableRoute(int idRoute);

    void sendMessage(String message);

    void handleClaimRoute();

    void handleDrawCard();
}
