package it.unibo.controller.gamecontroller.api;

import it.unibo.commons.Pair;

import java.awt.Color;
import java.util.Set;

public interface StartController {
    void startView();

    void closeView();

    void startGame(Set<Pair<String, Color>> players);

    MainController getMainController();
}
