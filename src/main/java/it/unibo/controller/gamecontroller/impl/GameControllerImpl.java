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
import it.unibo.controller.drawcontroller.api.DrawController;
import it.unibo.controller.drawcontroller.impl.DrawControllerImpl;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.route.api.Route;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;
import it.unibo.view.MainView;

/**
 * Implementation of {@link GameController}.
 * It models the game controller that allows the view comunicate with the model
 */
public class GameControllerImpl implements GameController {

    final private MainController mainController;
    final private List<Pair<String, Color>> tempPlayers = new ArrayList<>();

    private MainView view;

    private DrawController drawController = new DrawControllerImpl();

    /**
     * Simple constructor of the controller of the game logic
     * 
     * @param mainController the main controller of the aplication
     */
    public GameControllerImpl(final MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainCard handleDrawTrainCard() {
        TrainCard card = drawController.drawTrainCard();
        this.mainController.getTurnController().getCurrentPlayer().addTrainCard(card);
        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveCard handleDrawObjectiveCard() {
        Boolean drawn = false;
        ObjectiveCard card;

        do {
            card = drawController.drawObjectiveCard();
            drawn = this.mainController.getTurnController().getCurrentPlayer().addObjectiveCard(card);
        } while (!drawn);

        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        mainController.getTurnController().endTurn();
        view.refreshPlayerInterface();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, Integer>> getScore() {
        final ScoreCalculator scoreCalculator = new ScoreCalculatorImpl();
        return scoreCalculator.getScoreBoard(mainController.getGameInstance().getPlayers());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        view.launchPlayerSlect();
        tempPlayers.clear();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canStart() {
        return tempPlayers.size() >= 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, Color>> getTempPlayers() {
        return Collections.unmodifiableList(this.tempPlayers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        view.closeMainView();
        view.launchScoreBoard();
    }

    /**
     * {@inheritDoc}
     */
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
