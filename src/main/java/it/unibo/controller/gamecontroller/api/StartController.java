package it.unibo.controller.gamecontroller.api;

public interface StartController {
    void startView();

    void closeView();

    void startGame();

    MainController getMainController();
}
