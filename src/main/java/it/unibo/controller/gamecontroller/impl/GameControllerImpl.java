package it.unibo.controller.gamecontroller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.commons.Region;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.phasecontroller.impl.PhaseControllerImpl;
import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
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
public class GameControllerImpl implements GameController, Cloneable {

    private static final int NUM_MAX_PLAYER = 6;
    private final MainController mainController;
    private final List<Pair<String, Color>> tempPlayers = new ArrayList<>();
    private MainView view;
    private boolean isLastTurn;
    private boolean gameEnded;

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
    public GameControllerImpl clone() {
        try {
            return (GameControllerImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainCard handleDrawTrainCard() {
        final TrainCard card = this.mainController.getDrawController().drawTrainCard();
        this.mainController.getTurnController().getCurrentPlayer().addTrainCard(card);
        this.mainController.getPhaseController().switchPhase();
        view.refreshAllMainStage();

        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveCard handleDrawObjectiveCard() {
        Boolean drawn;
        ObjectiveCard card;

        this.mainController.getPhaseController().switchPhase();
        view.refreshAllMainStage();

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
        view.refreshAllMainStage();
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
        view.closeMainStage();
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

        routeSet.forEach(x -> x.getRailUnits().forEach(y -> regionSet.add(new Region(y.xCoord(), y.yCoord(), y.width(),
                y.length(), y.angle(), x.getId(), x.getColor(), this.getRouteClaimerColor(x)))));

        return regionSet;
    }

    /**
     * This method is used to get the color of the player that has claimed a route.
     * 
     * @param routeToFind the route to find
     * @return the color of the player that has claimed the route
     */
    private Optional<Color> getRouteClaimerColor(final Route routeToFind) {
        final List<PlayerImpl> playerList = this.mainController.getGameInstance().getPlayers();
        for (final var player : playerList) {
            for (final var route : player.getCompletedRoutes()) {
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
        view.refreshAllMainStage();
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
    public Set<Pair<Double, Double>> getPlayerCities(final Player currentPlayer) {
        final Set<Pair<Double, Double>> retSet = new LinkedHashSet<>();
        final Set<Route> routeSet = currentPlayer.getCompletedRoutes();

        for (final var route : routeSet) {
            final Pair<City, City> cities = route.getConnectedCity();
            Pair<Double, Double> cityCoords;
            final double cityRadius = this.getCityRadius();
            cityCoords = new Pair<>(cities.first().getCoordinates().first() - cityRadius,
                    cities.first().getCoordinates().second() - cityRadius);
            retSet.add(cityCoords);
            cityCoords = new Pair<>(cities.second().getCoordinates().first() - cityRadius,
                    cities.second().getCoordinates().second() - cityRadius);
            retSet.add(cityCoords);
        }

        return retSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getCityRadius() {
        return mainController.getGameInstance().getRoutes().get(0).getConnectedCity().first().getRadius();
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
        this.view.closeScoreBoard();
    }
}
