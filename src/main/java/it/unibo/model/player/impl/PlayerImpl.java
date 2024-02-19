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

import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
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
    private int routeScore;

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
    public PlayerImpl(final String name, final Color color, final int carriageNum) {
        this.name = name;
        this.color = color;
        this.objectiveCards = new LinkedHashSet<>();
        this.completedRoutes = new LinkedHashSet<>();
        this.carriageNum = carriageNum;
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
    public PlayerImpl(final String name, final int carriageNum) {
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
        Map<Color, Integer> trainCardList = new HashMap<>(trainCards);
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
    public int getCarriageNum() {
        return this.carriageNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarriageNum(final int number) {
        this.carriageNum = number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getObjectiveScore() {

        // Set<City> startCities = objectiveCards.stream().filter(x ->
        // x.isCompleted()).map(x -> x.getCities().first())
        // .collect(Collectors.toSet());
        // Set<City> targetCities = objectiveCards.stream().filter(x ->
        // x.isCompleted()).map(x -> x.getCities().second())
        // .collect(Collectors.toSet());

        // if (startCities.size() > 0 && targetCities.size() > 0) {
        // DirectedWeightedPseudograph<City, Route> tempGraph = new
        // DirectedWeightedPseudograph<>(RouteImpl.class);
        // playerGraph.vertexSet().stream().map(x -> tempGraph.addVertex(x));
        // playerGraph.edgeSet().stream()
        // .map(x -> tempGraph.addEdge(x.getConnectedCity().first(),
        // x.getConnectedCity().second(), x));

        // AllDirectedPaths<City, Route> allDirectedPaths = new
        // AllDirectedPaths<>(tempGraph);
        // System.out.println(allDirectedPaths.getAllPaths(startCities, targetCities,
        // true, null).stream()
        // .map(x -> x.getLength()).reduce(Integer::max).get());
        // }

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
    public int getRouteScore() {
        return this.routeScore;
    }

    /**
     * {@inheritDoc}
     */
    private void setRouteScore(final int number) {
        this.routeScore += number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrainCard(Color color, int num) {
        this.trainCards.replace(color, this.trainCards.get(color) - num);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrainCard(TrainCard card) {
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
                    && playerGraph.vertexSet().contains(objective.getCities().second())) {
                if (this.shortestPath
                        .getPath(objective.getCities().first(), objective.getCities().second()) != null) {
                    objective.setCompleted();
                }
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addObjectiveCard(ObjectiveCard card) {
        return this.objectiveCards.add(card);
    }

}
