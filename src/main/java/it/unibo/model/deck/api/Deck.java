package it.unibo.model.deck.api;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.card.api.*;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

/**
 * Regulates the functioning of the decks.
 */
public interface Deck {

    /**
     * @return the drawn card
     */
    TrainCard drawTrainCard();

    /**
     * @param graph the graph of the game
     * @return the drawn objectiveCard
     */
    ObjectiveCard drawObjectiveCard(WeightedPseudograph<City, Route> graph);

}