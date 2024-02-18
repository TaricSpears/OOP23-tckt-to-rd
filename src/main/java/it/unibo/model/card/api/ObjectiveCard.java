package it.unibo.model.card.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

/**
 * Represents an objective card in the game.
 */
public interface ObjectiveCard extends Card {

    /**
     * @return the score value of the objective card;
     */
    double getScore();

    /**
     * @return the pair of cities of the objective card.
     */
    Pair<City, City> getCities();

    /**
     * Sets the objective card as completed.
     */
    void setCompleted();

    /**
     * @return true if the objective card is completed, false otherwise.
     */
    boolean isCompleted();
}
