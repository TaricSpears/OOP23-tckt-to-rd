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
import java.util.Objects;
import java.util.Set;

import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.WeightedPseudograph;

import java.awt.Color;

/**
 * Implementation of {@link Player}.
 * 
 * Regulates the player's actions and attributes.
 */
public class PlayerImpl implements Player, Cloneable {

    private final String name;
    private final Color color;
    private final Map<Color, Integer> trainCards;
    private final Set<ObjectiveCard> objectiveCards;
    private final Set<Route> completedRoutes;
    private IntegerWrapper carriageNum;
    private IntegerWrapper routeScore;

    private final WeightedPseudograph<City, Route> playerGraph;
    private final BellmanFordShortestPath<City, Route> shortestPath;

    /**
     * Constructor for the player.
     * 
     * @param name        the name of the player.
     * 
     * @param color       the color of the player.
     * 
     * @param carriageNum the number of carriages of the player.
     */
    public PlayerImpl(final String name, final Color color, final Integer carriageNum) {
        this.name = name;
        this.color = color;
        this.objectiveCards = new LinkedHashSet<>();
        this.completedRoutes = new LinkedHashSet<>();
        this.carriageNum = new IntegerWrapper(carriageNum);
        this.routeScore = new IntegerWrapper(0);
        this.playerGraph = new WeightedPseudograph<>(RouteImpl.class);

        this.trainCards = new HashMap<>();
        this.trainCards.put(Color.BLACK, 1);
        this.trainCards.put(Color.BLUE, 1);
        this.trainCards.put(Color.GREEN, 1);
        this.trainCards.put(Color.RED, 1);
        this.trainCards.put(Color.WHITE, 1);
        this.trainCards.put(Color.YELLOW, 1);
        this.trainCards.put(Color.ORANGE, 1);
        this.trainCards.put(Color.MAGENTA, 1);
        this.trainCards.put(Color.DARK_GRAY, 0);

        this.shortestPath = new BellmanFordShortestPath<>(playerGraph);
    }

    /**
     * Constructor for the player without a given color.
     * 
     * @param name        the name of the player.
     * 
     * @param carriageNum the number of carriages of the player.
     */
    public PlayerImpl(final String name, final Integer carriageNum) {
        this(name, Color.BLACK, carriageNum);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Color, Integer> getTrainCards() {
        final Map<Color, Integer> trainCardList = new HashMap<>(trainCards);
        return Collections.unmodifiableMap(trainCardList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ObjectiveCard> getObjectiveCards() {
        return Collections.unmodifiableSet(objectiveCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Route> getCompletedRoutes() {
        return Collections.unmodifiableSet(completedRoutes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCarriageNum() {
        return this.carriageNum.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarriageNum(final Integer number) {
        this.carriageNum.setValue(number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getObjectiveScore() {
        double objectiveScore = 0;
        for (final ObjectiveCard objective : objectiveCards) {
            if (objective.isCompleted()) {
                objectiveScore += objective.getScore();
            } else {
                objectiveScore -= objective.getScore();
            }
        }

        return objectiveScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getRouteScore() {
        return this.routeScore.getValue();
    }

    /**
     * Adds the passed number to the score of the routes.
     * 
     * @param number
     */
    private void setRouteScore(final Integer number) {
        this.routeScore.setValue(this.routeScore.getValue() + number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrainCard(final Color color, final Integer num) {
        this.trainCards.replace(color, this.trainCards.get(color) - num);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrainCard(final TrainCard card) {
        this.trainCards.replace(card.getColor(), this.trainCards.get(card.getColor()) + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRoute(final Route route) {
        final City city1 = route.getConnectedCity().first();
        final City city2 = route.getConnectedCity().second();

        playerGraph.addVertex(city1);
        playerGraph.addVertex(city2);
        playerGraph.addEdge(city1, city2, route);
        playerGraph.setEdgeWeight(route, route.getScore());

        this.completedRoutes.add(route);
        this.setRouteScore(route.getScore());

        for (final ObjectiveCard objective : objectiveCards) {
            if (!objective.isCompleted() && playerGraph.vertexSet().contains(objective.getCities().first())
                    && playerGraph.vertexSet().contains(objective.getCities().second())
                    && this.shortestPath.getPath(objective.getCities().first(),
                            objective.getCities().second()) != null) {
                objective.setCompleted();
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addObjectiveCard(final ObjectiveCard card) {
        return this.objectiveCards.add(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerImpl clone() {
        try {
            return (PlayerImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        final Player player = (Player) obj;
        return this.name.equals(player.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.color);
    }

    /**
     * Wrapper for Integer.
     */
    public static class IntegerWrapper {

        private int value;

        /**
         * Constructor for the wrapper.
         * 
         * @param value the int value to wrap.
         */
        public IntegerWrapper(final int value) {
            this.value = value;
        }

        /**
         * @return the value of the wrapper.
         */
        public int getValue() {
            return value;
        }

        /**
         * Sets the value of the wrapper.
         * 
         * @param value the value to set.
         */
        public void setValue(final int value) {
            this.value = value;
        }
    }
}
