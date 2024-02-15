package it.unibo.controller.gamecontroller.api;

import it.unibo.commons.Pair;

import java.awt.Color;
import java.util.List;

public interface StartController {
    void startView();

    void closeView();

    void startGame(List<Pair<String, Color>> players);

    MainController getMainController();
}
