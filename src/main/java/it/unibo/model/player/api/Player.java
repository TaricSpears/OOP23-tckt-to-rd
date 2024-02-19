package it.unibo.model.player.api;

import java.util.Set;
import java.util.Map;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.route.api.Route;

import java.awt.Color;

/**
 * Regulates the player's actions and attributes.
 */
public interface Player {

    /**
     * @return the name of the player.
     */
    String getName();

    /**
     * @return the color of the player.
     */
    Color getColor();

    /**
     * @return the map of train cards of the player.
     */
    Map<Color, Integer> getTrainCards();

    /**
     * Removes a train card from the player's hand.
     * 
     * @param color the color of the card to remove.
     * 
     * @param num   the number of cards to remove.
     */
    void removeTrainCard(Color color, int num);

    /**
     * @return the set of objective cards of the player.
     */
    Set<ObjectiveCard> getObjectiveCards();

    /**
     * @return the set of completed routes of the player.
     */
    Set<Route> getCompletedRoutes();

    /**
     * @return the number of carriages of the player.
     */
    int getCarriageNum();

    /**
     * Sets the number of carriages of the player.
     * 
     * @param number the number of carriages of the player.
     */
    void setCarriageNum(int number);

    /**
     * @return the score of the objective cards.
     */
    double getObjectiveScore();

    /**
     * @return the score of the routes.
     */
    int getRouteScore();

    /**
     * Adds the drawn Train card to the player's hand.
     * 
     * @param card the drawn card.
     */
    void addTrainCard(TrainCard card);

    /**
     * Adds the drawn Objective card to the player's hand.
     * 
     * @param card the drawn card.
     * 
     * @return true if the card was added, false otherwise.
     */
    boolean addObjectiveCard(ObjectiveCard card);

    /**
     * Adds the route to the current player (the list and the player's graph) and
     * checks if the objective is been completed
     * 
     * @param route the route to be added to the player
     */
    void addRoute(Route route);
}
