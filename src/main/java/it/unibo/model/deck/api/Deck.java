package it.unibo.model.deck.api;

import it.unibo.model.card.api.*;

/**
 * Regulates the functioning of the decks.
 */
public interface Deck {

    /**
     * @return the drawn card
     */
    TrainCard drawTrainCard();

    /**
     * @return the drawn objectiveCard
     */
    ObjectiveCard drawObjectiveCard();

}