package it.unibo.model.player.impl;

import it.unibo.model.player.api.Player;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.WeightedPseudograph;

import java.awt.Color;

/**
 * Implementation of {@link Player}.
 * 
 * Regulates the player's actions and attributes.
 */
public class PlayerImpl implements Player {

    private final String name;
    private final Color color;
    private final Map<Color, Integer> trainCards;
    private final Set<ObjectiveCard> objectiveCards;
    private final Set<Route> completedRoutes;
    private int carriageNum;
    private int objectiveScore;
    private int routeScore;

    private final WeightedPseudograph<City, Route> playerGraph;

    /**
     * Constructor for the player.
     * 
     * @param name        the name of the player.
     * 
     * @param color       the color of the player.
     * 
     * @param carriageNum the number of carriages of the player.
     */
    public PlayerImpl(final String name, final Color color, final int carriageNum) {
        this.name = name;
        this.color = color;
        this.objectiveCards = new LinkedHashSet<>();
        this.completedRoutes = new LinkedHashSet<>();
        this.carriageNum = carriageNum;
        this.objectiveScore = 0;
        this.routeScore = 0;
        this.playerGraph = new WeightedPseudograph<>(RouteImpl.class);

        this.trainCards = new HashMap<>();
        this.trainCards.put(Color.BLACK, 1);
        this.trainCards.put(Color.BLUE, 1);
        this.trainCards.put(Color.GREEN, 1);
        this.trainCards.put(Color.RED, 1);
        this.trainCards.put(Color.WHITE, 1);
        this.trainCards.put(Color.YELLOW, 1);
        this.trainCards.put(Color.ORANGE, 1);
        this.trainCards.put(Color.PINK, 1);
        this.trainCards.put(Color.DARK_GRAY, 0);
    }

    /**
     * Constructor for the player without a given color.
     * 
     * @param name        the name of the player.
     * 
     * @param carriageNum the number of carriages of the player.
     */
    public PlayerImpl(final String name, final int carriageNum) {
        this(name, Color.BLACK, carriageNum);
    }

    /**
     * @return the name of the player.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the color of the player.
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the map of train cards of the player.
     */
    @Override
    public Map<Color, Integer> getTrainCards() {
        Map<Color, Integer> trainCardList = new HashMap<>(trainCards);
        return Collections.unmodifiableMap(trainCardList);
    }

    /**
     * @return the set of objective cards of the player.
     */
    @Override
    public Set<ObjectiveCard> getObjectiveCards() {
        return Collections.unmodifiableSet(objectiveCards);
    }

    /**
     * @return the set of completed routes of the player.
     */
    @Override
    public Set<Route> getCompletedRoutes() {
        return Collections.unmodifiableSet(completedRoutes);
    }

    /**
     * @return the number of carriages of the player.
     */
    @Override
    public int getCarriageNum() {
        return this.carriageNum;
    }

    /**
     * Sets the number of carriages of the player.
     * 
     * @param number the number of carriages of the player.
     */
    @Override
    public void setCarriageNum(final int number) {
        this.carriageNum = number;
    }

    /**
     * @return the score of the objective cards.
     */
    @Override
    public int getObjectiveScore() {
        return this.objectiveScore;
    }

    /**
     * @param number the score of the objective cards.
     */
    private void setObjectiveScore(final double number) {
        this.objectiveScore += number;
    }

    /**
     * @return the score of the routes.
     */
    @Override
    public int getRouteScore() {
        return this.routeScore;
    }

    /**
     * @param number the score of the routes.
     */
    private void setRouteScore(final int number) {
        this.routeScore += number;
    }

    /*
     * @param add route to the player's completed routes.
     */
    public void addRoute(final Route route) {
        completedRoutes.add(route);
    }

    /*
     * Removes a train card from the player's hand.
     * 
     * @param color the color of the card to remove.
     * 
     * @param num   the number of cards to remove.
     */
    @Override
    public void removeTrainCard(Color color, int num) {
        this.trainCards.replace(color, this.trainCards.get(color) - num);
    }

    /**
     * Adds the drawn Train card to the player's hand.
     * 
     * @param card the drawn card.
     */
    @Override
    public void addTrainCard(TrainCard card) {
        this.trainCards.replace(card.getColor(), this.trainCards.get(card.getColor()) + 1);
    }

    @Override
    public void addRouteAncCheckObjective(final Route route) {
        final City city1 = route.getConnectedCity().first();
        final City city2 = route.getConnectedCity().second();

        playerGraph.addVertex(city1);
        playerGraph.addVertex(city2);
        playerGraph.addEdge(city1, city2, route);
        playerGraph.setEdgeWeight(city1, city2, route.getScore());

        this.completedRoutes.add(route);
        this.setRouteScore(route.getScore());

        for (final ObjectiveCard objective : objectiveCards) {
            final Set<City> playersCities = playerGraph.vertexSet();
            if (playersCities.contains(objective.getCities().first())
                    && playersCities.contains(objective.getCities().second())) {
                objective.setCompleted();
                this.setObjectiveScore(objective.getScore());
            }
        }
    }

    @Override
    public boolean addObjectiveCard(ObjectiveCard card) {
        return this.objectiveCards.add(card);
    }
}
