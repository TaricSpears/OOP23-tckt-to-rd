package it.unibo.controller.gamecontroller.api;

import it.unibo.commons.Pair;
import it.unibo.model.gameprep.impl.GamePrep;

import java.awt.Color;
import java.util.List;

public interface StartController {
    void startView();

    void startGame(List<Pair<String, Color>> players);

    MainController getMainController();

    GamePrep getGameInstance();
}
