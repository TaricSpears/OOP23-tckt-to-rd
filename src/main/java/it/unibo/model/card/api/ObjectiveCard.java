package it.unibo.model.card.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

/*
 * Represents an objective card in the game.
 */
public interface ObjectiveCard extends Card {

    /*
     * @return the score value of the objective card;
     */
    double getScore();

    Pair<City, City> getCities();
}
