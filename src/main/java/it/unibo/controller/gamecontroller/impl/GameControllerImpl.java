package it.unibo.controller.gamecontroller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.commons.Region;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.route.api.Route;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;
import it.unibo.view.MainView;

public class GameControllerImpl implements GameController {

    final private MainController mainController;
    final private List<Pair<String, Color>> tempPlayers = new ArrayList<>();

    private MainView view;

    public GameControllerImpl(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void endTurn() {
        mainController.getTurnController().endTurn();
        view.refreshPlayerInterface();
    }

    @Override
    public List<Pair<String, Integer>> getScore() {
        final ScoreCalculator scoreCalculator = new ScoreCalculatorImpl();
        return scoreCalculator.getScoreBoard(mainController.getGameInstance().getPlayers());
    }

    @Override
    public void newGame() {
        view.launchPlayerSlect();
        tempPlayers.clear();
    }

    @Override
    public boolean addPlayer(Pair<String, Color> player) {
        if (tempPlayers.stream().anyMatch(
                x -> x.first().equals(player.first()) || x.second().equals(player.second()))
                || tempPlayers.size() >= 6) {
            return false;
        }
        tempPlayers.add(player);
        return true;
    }

    @Override
    public boolean canStart() {
        return tempPlayers.size() >= 2;
    }

    @Override
    public List<Pair<String, Color>> getTempPlayers() {
        return Collections.unmodifiableList(this.tempPlayers);
    }

    @Override
    public void endGame() {
        view.closeMainView();
        view.launchScoreBoard();
    }

    @Override
    public void addView(MainView view) {
        this.view = view;
    }

    @Override
    public Set<Region> getRegions() {
        final List<Route> routeSet = mainController.getGameInstance().getRoutes();
        final Set<Region> regionSet = new LinkedHashSet<>();
        Route route;
        Iterator<Carriage> routeIterator;
        Carriage carriage;
        for(int i=0; i<routeSet.size(); i++){
            route = routeSet.get(i);
            routeIterator = route.getRailUnits().iterator();
            while(routeIterator.hasNext()){
                carriage = routeIterator.next();
                regionSet.add(new Region(carriage.xCoord(), carriage.yCoord(),
                     carriage.width(), carriage.length(), carriage.angle(),
                     route.getId(), route.getColor()));
            }
        }
        return regionSet;
    }
}
