package it.unibo.controller.gamecontroller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
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
}
