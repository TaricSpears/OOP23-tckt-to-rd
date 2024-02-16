package it.unibo.controller.gamecontroller.api;

import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;

public interface StartController {
    void startView();

    void startGame();

    MainController getMainController();

    GamePrep getGameInstance();

    void setMainApp(MainView app);
}
