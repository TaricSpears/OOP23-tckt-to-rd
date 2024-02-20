package it.unibo.controller.gamecontroller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.commons.Region;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.phasecontroller.impl.PhaseControllerImpl;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;
import it.unibo.view.MainView;

/**
 * Implementation of {@link GameController}.
 * It models the game controller that allows the view comunicate with the model.
 */
public class GameControllerImpl implements GameController {

    private static final int NUM_MAX_PLAYER = 6;
    private final MainController mainController;
    private final List<Pair<String, Color>> tempPlayers = new ArrayList<>();
    private MainView view;
    private boolean isLastTurn = false;
    private boolean gameEnded = false;

    /**
     * Simple constructor of the controller of the game logic.
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
        TrainCard card = this.mainController.getDrawController().drawTrainCard();
        this.mainController.getTurnController().getCurrentPlayer().addTrainCard(card);
        this.mainController.getPhaseController().switchPhase();
        view.refreshAll();

        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveCard handleDrawObjectiveCard() {
        Boolean drawn = false;
        ObjectiveCard card;

        this.mainController.getPhaseController().switchPhase();
        view.refreshAll();

        do {
            card = this.mainController.getDrawController()
                    .drawObjectiveCard(mainController.getGameInstance().getGraph());
            drawn = this.mainController.getTurnController().getCurrentPlayer().addObjectiveCard(card);
        } while (!drawn);

        view.refreshPlayerInterface();
        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        if (this.isLastTurn && this.mainController.getTurnController().wasLastPlayer()) {
            this.endGame();
            this.gameEnded = true;
        }
        this.mainController.getTurnController().endTurn();

        this.mainController.setPhaseController(new PhaseControllerImpl());
        this.view.refreshAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, Double>> getScore() {
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
        this.isLastTurn = false;
        this.gameEnded = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addPlayer(final Pair<String, Color> player) {
        if (tempPlayers.stream().anyMatch(
                x -> x.first().equals(player.first()) || x.second().equals(player.second()))
                || tempPlayers.size() >= NUM_MAX_PLAYER || player.first().isBlank()) {
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
    public void addView(final MainView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Region> getRegions() {
        final List<Route> routeSet = mainController.getGameInstance().getRoutes();
        final Set<Region> regionSet = new LinkedHashSet<>();
        Route route;
        Iterator<Carriage> routeIterator;
        Carriage carriage;
        for (int i = 0; i < routeSet.size(); i++) {
            route = routeSet.get(i);
            routeIterator = route.getRailUnits().iterator();
            while (routeIterator.hasNext()) {
                carriage = routeIterator.next();
                regionSet.add(new Region(carriage.xCoord(), carriage.yCoord(),
                        carriage.width(), carriage.length(), carriage.angle(),
                        route.getId(), route.getColor(), this.getRouteClaimerColor(route)));
            }
        }
        return regionSet;
    }

    /**
     * This method is used to get the color of the player that has claimed a route.
     * 
     * @param routeToFind the route to find
     * @return the color of the player that has claimed the route
     */
    private Optional<Color> getRouteClaimerColor(final Route routeToFind) {
        final List<Player> playerList = this.mainController.getGameInstance().getPlayers();
        for (var player : playerList) {
            for (var route : player.getCompletedRoutes()) {
                if (routeToFind.equals(route)) {
                    return Optional.of(player.getColor());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshView() {
        this.view.refreshAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastTurn() {
        this.isLastTurn = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastTurn() {
        return this.isLastTurn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameEnded() {
        return this.gameEnded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeGame() {
        this.view.closeFinalScoreBoard();
    }
}
