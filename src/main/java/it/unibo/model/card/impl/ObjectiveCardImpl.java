package it.unibo.model.card.impl;

import it.unibo.commons.Pair;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.city.api.City;

public class ObjectiveCardImpl implements ObjectiveCard {

    private final Pair<City, City> objective;
    private final int scoreValue;
    private boolean completed;

    public ObjectiveCardImpl(final Pair<City, City> objective, final int scoreValue) {

        this.objective = objective;
        this.scoreValue = scoreValue;
        this.completed = false;
    }

    public int getScore() {
        return this.scoreValue;
    }

}
