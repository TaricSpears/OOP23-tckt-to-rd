package it.unibo.model.player.impl;

import it.unibo.model.player.api.Player;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.route.api.Route;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import java.awt.Color;

/*
 * Implementation of {@link Player}.
 * 
 * Regulates the player's actions and attributes.
 */
public class PlayerImpl implements Player {

    private final String name;
    private final Color color;
    private final List<TrainCard> trainCards;
    private final Set<ObjectiveCard> objectiveCards;
    private final Set<Route> completedRoutes;
    private int carriageNum;
    private int objectiveScore;
    private int routeScore;

    /*
     * Constructor for the player.
     * 
     * @param name the name of the player.
     * 
     * @param color the color of the player.
     * 
     * @param carriageNum the number of carriages of the player.
     */
    public PlayerImpl(final String name, final Color color, final int carriageNum) {
        this.name = name;
        this.color = color;
        this.trainCards = new LinkedList<>();
        this.objectiveCards = new LinkedHashSet<>();
        this.completedRoutes = new LinkedHashSet<>();
        this.carriageNum = carriageNum;
        this.objectiveScore = 0;
        this.routeScore = 0;
    }

    /*
     * Constructor for the player without a given color.
     * 
     * @param name the name of the player.
     * 
     * @param carriageNum the number of carriages of the player.
     */
    public PlayerImpl(final String name, final int carriageNum) {
        this(name, Color.BLACK, carriageNum);
    }

    /*
     * @return the name of the player.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * @return the color of the player.
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /*
     * @return the list of train cards of the player.
     */
    @Override
    public List<TrainCard> getTrainCards() {
        List<TrainCard> trainCardList = new LinkedList<>(trainCards);
        return Collections.unmodifiableList(trainCardList);
    }

    /*
     * @return the set of objective cards of the player.
     */
    @Override
    public Set<ObjectiveCard> getObjectiveCards() {
        return Collections.unmodifiableSet(objectiveCards);
    }

    /*
     * @return the set of completed routes of the player.
     */
    @Override
    public Set<Route> getCompletedRoutes() {
        return Collections.unmodifiableSet(completedRoutes);
    }

    /*
     * @return the number of carriages of the player.
     */
    @Override
    public int getCarriageNum() {
        return this.carriageNum;
    }

    /*
     * Sets the number of carriages of the player.
     * 
     * @param number the number of carriages of the player.
     */
    @Override
    public void setCarriageNum(final int number) {
        this.carriageNum = number;
    }

    /*
     * @return the score of the objective cards.
     */
    @Override
    public int getObjectiveScore() {
        return this.objectiveScore;
    }

    /*
     * @param number the score of the objective cards.
     */
    @Override
    public void setObjectiveScore(final double number) {
        this.objectiveScore += number;
    }

    /*
     * @return the score of the routes.
     */
    @Override
    public int getRouteScore() {
        return this.routeScore;
    }

    /*
     * @param number the score of the routes.
     */
    @Override
    public void setRouteScore(final int number) {
        this.routeScore += number;
    }

    /*
     * Removes a train card from the player's hand.
     * 
     * @param color the color of the card to remove.
     */
    @Override
    public void removeTrainCard(Color color) {
        trainCards.removeIf(card -> card.getColor().equals(color));
    }
}
