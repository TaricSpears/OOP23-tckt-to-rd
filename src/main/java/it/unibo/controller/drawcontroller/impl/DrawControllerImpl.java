package it.unibo.controller.drawcontroller.impl;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.controller.drawcontroller.api.DrawController;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.city.api.City;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.route.api.Route;

/**
 * Implementation of {@link DrawController}.
 * Interface to control card drawing.
 */
public class DrawControllerImpl implements DrawController {

    private final Deck deck = new DeckImpl();

    /**
     * @return a Train card from the deck.
     */
    @Override
    public TrainCard drawTrainCard() {
        return this.deck.drawTrainCard();
    }

    /**
     * @return an Objective card from the deck.
     */
    @Override
    public ObjectiveCard drawObjectiveCard(final WeightedPseudograph<City, Route> graph) {
        return this.deck.drawObjectiveCard(graph);
    }

}
