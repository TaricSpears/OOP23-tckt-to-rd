package it.unibo.model.card.impl;

import it.unibo.commons.Pair;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.city.api.City;

/*
 * Implementation of {@link ObjectiveCard}.
 * Represents an objective card in the game.
 */
public class ObjectiveCardImpl implements ObjectiveCard {

    private final Pair<City, City> objective;
    private final int scoreValue;
    private boolean completed;

    /*
     * Constructor for the objective card.
     * 
     * @param objective the pair of cities that the player has to connect.
     * 
     * @param scoreValue the score value of the objective card.
     */
    public ObjectiveCardImpl(final Pair<City, City> objective, final int scoreValue) {

        this.objective = objective;
        this.scoreValue = scoreValue;
        this.completed = false;
    }

    /*
     * @return the score value of the objective card;
     */
    @Override
    public int getScore() {
        return this.scoreValue;
    }

    /*
     * @return the type of the card
     */
    @Override
    public Type getType() {
        return Type.OBJECTIVE;
    }

}
