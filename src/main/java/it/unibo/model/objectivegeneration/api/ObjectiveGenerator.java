package it.unibo.model.objectivegeneration.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

public interface ObjectiveGenerator {

    /**
     * @return a new objective card
     */
    Pair<City, City> generateObjective();

    double calculateScore(Pair<City, City> objective);
}
