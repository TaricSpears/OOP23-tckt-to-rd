package it.unibo.controller.gamecontroller.api;

import java.util.List;
import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.commons.Region;
import it.unibo.view.MainView;

public interface GameController {

    void endTurn();

    List<Pair<String, Integer>> getScore();

    void newGame();

    boolean addPlayer(Pair<String, java.awt.Color> pair);

    boolean canStart();

    List<Pair<String, java.awt.Color>> getTempPlayers();

    void endGame();

    void addView(MainView view);

    Set<Region> getRegions();
}
