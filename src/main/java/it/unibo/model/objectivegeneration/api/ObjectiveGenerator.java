package it.unibo.model.objectivegeneration.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

/**
 * Generates the objective cards.
 */
public interface ObjectiveGenerator {

    /**
     * Generates a new objective card.
     * 
     * @return a new objective card.
     */
    Pair<City, City> generateObjective();

    /**
     * Calculates the score of the objective.
     * 
     * @param objective
     * @return the score of the objective.
     */
    double calculateScore(Pair<City, City> objective);
}
