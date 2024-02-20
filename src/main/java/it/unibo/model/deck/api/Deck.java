package it.unibo.model.deck.api;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

/**
 * Regulates the functioning of the decks.
 */
public interface Deck {

    /**
     * Draws a train card from the deck.
     * 
     * @return the drawn card
     */
    TrainCard drawTrainCard();

    /**
     * Draws an objective card from the deck.
     * 
     * @param graph the graph of cities and routes of the game.
     * @return the drawn card
     */
    ObjectiveCard drawObjectiveCard(WeightedPseudograph<City, Route> graph);

}
